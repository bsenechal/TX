package com.utc.api01.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.utc.api01.model.Notes;
import com.utc.api01.model.Question;
import com.utc.api01.service.GeneriqueService;

@Controller
public class QuestionsController {
    private GeneriqueService<Question> questionService;
    private GeneriqueService<Notes> noteService;
    private static final String REDIRECT_EDITQUESTION = "editQuestion";
    private static final String JSP_QUESTION = "question";
    private static final String LIST_QUESTIONS = "listQuestions";
    private static final String MSG_ADD_SUCCESS = "La question a correctement été ajoutée.";
    private static final String MSG_EDIT_SUCCESS = "La question a correctement été modifiée.";
    private static final String MSG_SUPPR_SUCCESS = "La question a correctement été supprimée.";
    
    @Autowired(required = true)
    @Qualifier(value = "questionService")
    public void setQuestionService(GeneriqueService<Question> q) {
        this.questionService = q;
    }
    
    @Autowired(required = true)
    @Qualifier(value = "noteService")
    public void setNoteService(GeneriqueService<Notes> n) {
        this.noteService = n;
    }
    
    @RequestMapping(value = "/admin/questions", method = RequestMethod.GET)
    public String listQuestions(Model model) {
        model.addAttribute(LIST_QUESTIONS, this.questionService.list());
        return JSP_QUESTION;
    }

    @RequestMapping("/admin/questions/remove/{idQuest}")
    public ModelAndView removeQuestion(@PathVariable("idQuest") int idQuest) {
        ModelAndView model = new ModelAndView();
        model.addObject("msg", MSG_SUPPR_SUCCESS);
        model.setViewName(JSP_QUESTION);
        
        for (Notes n : this.noteService.list()){
            if (n.getQuestion().getIdQuestions() == idQuest){
                this.noteService.remove(n.getIdNotes());
            }
        }
        
        this.questionService.remove(idQuest);
        model.addObject(LIST_QUESTIONS, this.questionService.list());
        return model;
    }

    @RequestMapping(value = "/admin/questions/addQuestion", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute(JSP_QUESTION, new Question());
        return REDIRECT_EDITQUESTION;
    }

    @RequestMapping("/admin/questions/edit/{idQuestion}")
    public String editQuestion(@PathVariable("idQuestion") int id, Model model) {
        model.addAttribute(JSP_QUESTION, this.questionService.getById(id));
        return REDIRECT_EDITQUESTION;
    }

    @RequestMapping(value = "/admin/questions/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("question") Question q, BindingResult result) {
        ModelAndView model = new ModelAndView();
       
        if (result.hasErrors()) {
            if (q.getIdQuestions() != 0){
                model.addObject(JSP_QUESTION, q);
            }
            model.setViewName(REDIRECT_EDITQUESTION);
        } else {
            model.setViewName(JSP_QUESTION);
            
            if (q.getIdQuestions() != 0) {
                model.addObject("msg", MSG_EDIT_SUCCESS);
                this.questionService.update(q);
            } else {
                model.addObject("msg", MSG_ADD_SUCCESS);
                this.questionService.add(q);
            }
            model.addObject(LIST_QUESTIONS, this.questionService.list());
        }
        return model;
    }

}
