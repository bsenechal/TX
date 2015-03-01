package com.utc.api01.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.utc.api01.model.Book;
import com.utc.api01.model.Evaluation;
import com.utc.api01.model.Notes;
import com.utc.api01.model.Question;
import com.utc.api01.model.QuestionWrapper;
import com.utc.api01.model.User;
import com.utc.api01.service.GeneriqueService;

@Controller
public class EvaluationController {
    private GeneriqueService<Question> questionService;
    private GeneriqueService<Evaluation> evalService;
    private GeneriqueService<User> userService;
    private GeneriqueService<Book> bookService;
    private GeneriqueService<Notes> noteService;
    private static final String JSP_EVALUATION = "evaluation";
    private static final String JSP_NEWEVALUATION = "newEvaluation";
    private static final String JSP_MYBOOK = "myBook";
    private static final int ROLE_ADMIN = 1;
    private static final String JSP_DETAILBOOK = "detailBook";
    private static final String MSG_ADD_SUCCESS = "Votre évaluation a bien été prise en compte.";
    private static final String MSG_SUPPR_SUCCESS = "L'évaluation a correctement été supprimée.";
    private static final String QUESTION_WRAPPER = "questionWrapper";
    
    @Autowired(required = true)
    @Qualifier(value = "evalService")
    public void setEvaluationService(GeneriqueService<Evaluation> e) {
        this.evalService = e;
    }
    
    @Autowired(required = true)
    @Qualifier(value = "questionService")
    public void setQuestionService(GeneriqueService<Question> q) {
        this.questionService = q;
    }
    
    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(GeneriqueService<User> us) {
        this.userService = us;
    }
    
    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(GeneriqueService<Book> us) {
        this.bookService = us;
    }
    
    @Autowired(required = true)
    @Qualifier(value = "noteService")
    public void setNoteService(GeneriqueService<Notes> n) {
        this.noteService = n;
    }
    
    @RequestMapping("/evaluation/remove/{idEval}")
    public ModelAndView remove(@PathVariable("idEval") int idEval) {
        ModelAndView model = new ModelAndView();
        model.addObject("msg", MSG_SUPPR_SUCCESS);
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.User){
            User user = this.userService.getByCriteria("email", ((org.springframework.security.core.userdetails.User) principal).getUsername());
            
            if (user.getRole().getIdRole() == ROLE_ADMIN){
                model.setViewName(JSP_EVALUATION);
            } else {
                model.setViewName(JSP_MYBOOK);
            }
            for (Notes n : this.noteService.list()){
                if (n.getEvaluation().getIdEval() == idEval){
                    this.noteService.remove(n.getIdNotes());
                }
            }
        }
        this.evalService.remove(idEval);
        model.addObject("listEvals", this.evalService.list());
        return model;
    }

    @RequestMapping(value = "/admin/evaluations", method = RequestMethod.GET)
    public String listEvaluations(Model model) {
        model.addAttribute("listEvals", this.evalService.list());
        return JSP_EVALUATION;
    }

    @RequestMapping(value = "/evaluation/new/{idBook}", method = RequestMethod.GET)
    public String newEval(@PathVariable("idBook") int idBook, Model model) {
        
        model.addAttribute(QUESTION_WRAPPER, new QuestionWrapper(this.questionService.list()));
        model.addAttribute("book", this.bookService.getById(idBook));
        
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.User){
            
            User user = this.userService.getByCriteria("email", ((org.springframework.security.core.userdetails.User) principal).getUsername());
            List<Evaluation> listEvaluation = this.evalService.list();
            
            for (Evaluation eval : listEvaluation){
                if (eval.getUser().getIdUser() == user.getIdUser() && eval.getBook().getIdBook() == idBook){
                    model.addAttribute("dejaEdit", true);
                    model.addAttribute("idEval", eval.getIdEval());
                    break;
                }
            }
        }
        return JSP_NEWEVALUATION;
    }
    
    @RequestMapping(value = "/admin/evaluation/edit/{idBook}", method = RequestMethod.GET)
    public String editEval(@PathVariable("idBook") int idBook, Model model) {
        
        List<Question> listQuestion = new ArrayList<Question>();
        
        for(Notes n : this.noteService.list()){
            if (n.getEvaluation().getBook().getIdBook() == idBook){
                n.getQuestion().setNote(n.getNote());
                listQuestion.add(n.getQuestion());
            }
        }
       
        
        model.addAttribute(QUESTION_WRAPPER, new QuestionWrapper(listQuestion));
        model.addAttribute("book", this.bookService.getById(idBook));
        return JSP_NEWEVALUATION;
    }
    
    @RequestMapping(value = "/evaluation/save/{idBook}", method = RequestMethod.POST)
    public ModelAndView newEval(@Valid @ModelAttribute("notesWrapper") QuestionWrapper questionWrapper, @PathVariable("idBook") int idBook, BindingResult result) {
        ModelAndView model = new ModelAndView();
        
        if (result.hasErrors()) {
            questionWrapper.setQuestionList(this.questionService.list());
            model.addObject(QUESTION_WRAPPER, questionWrapper);
            model.setViewName(JSP_NEWEVALUATION);
        } else {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof org.springframework.security.core.userdetails.User){
                String userName = ((org.springframework.security.core.userdetails.User) principal).getUsername();
                User user = this.userService.getByCriteria("email", userName);
            
                this.evalService.add(new Evaluation(0,0, this.bookService.getById(idBook), user));
                
                List<Evaluation> listEval = this.evalService.list();
                
                for (Question quest : questionWrapper.getQuestionList()){
                    this.noteService.add(new Notes(0, quest.getNote(), listEval.get(listEval.size() - 1), this.questionService.getById(quest.getIdQuestions())));
                }
                model.addObject("book", this.bookService.getById(idBook));
                model.addObject("msg", MSG_ADD_SUCCESS);
                model.setViewName(JSP_DETAILBOOK);
            }
        }
        
        return model;
    }

}
