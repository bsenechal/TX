package com.utc.api01.matching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.utc.api01.model.Book;
import com.utc.api01.model.Evaluation;
import com.utc.api01.model.Notes;
import com.utc.api01.model.Question;

public class MatchFounder {
    
    private List<Book> bookList;
    private List<Question> questionList;
    private List<Notes> noteList;
    private List<Evaluation> evaluationList;
    
    List<Question> hightQuestion;
    List<Question> mediumQuestion;
    List<Question> lowQuestion;
    
    List<Book> hightBook;
    List<Book> mediumBook;
    List<Book> lowBook;
    
    String bestType;
    String bestAutor;
    
    private Map<Integer,List<Notes>> mapEval;
    Map<Question,Float> prctQuestion;
    
    private static final int HIGHT_RANGE = 4;
    private static final int MEDIUM_RANGE = 2;
    private static final int LOW_RANGE = 0;
    
    /**
     * @param bookList
     * @param questionList
     * @param noteList
     * @param evaluationList
     */
    public MatchFounder(List<Book> bookList,
            List<Question> questionList, List<Notes> noteList,
            List<Evaluation> evaluationList) {
        super();
        this.bookList = bookList;
        this.questionList = questionList;
        this.noteList = noteList;
        this.evaluationList = evaluationList;
    }
    /**
     * @return the bookList
     */
    public List<Book> getBookList() {
        return bookList;
    }
    /**
     * @param bookList the bookList to set
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
    /**
     * @return the questionList
     */
    public List<Question> getQuestionList() {
        return questionList;
    }
    /**
     * @param questionList the questionList to set
     */
    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
    /**
     * @return the noteList
     */
    public List<Notes> getNoteList() {
        return noteList;
    }
    /**
     * @param noteList the noteList to set
     */
    public void setNoteList(List<Notes> noteList) {
        this.noteList = noteList;
    }
    /**
     * @return the evaluationList
     */
    public List<Evaluation> getEvaluationList() {
        return evaluationList;
    }
    /**
     * @param evaluationList the evaluationList to set
     */
    public void setEvaluationList(List<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }
    
    private void setMapEvalAndQuestionRank(){
        
        mapEval = new HashMap<Integer,List<Notes>>();
        hightQuestion = new ArrayList<Question>();
        mediumQuestion = new ArrayList<Question>();
        lowQuestion = new ArrayList<Question>();
        
        
        List<Notes> lowRange = new ArrayList<Notes>();
        List<Notes> mediumRange = new ArrayList<Notes>();
        List<Notes> hightRange = new ArrayList<Notes>();
        
        for(Notes note : noteList){
            if(note.getNote() >= HIGHT_RANGE){
                if(!hightRange.contains(note.getEvaluation()))
                    hightRange.add(note);
                hightQuestion.add(note.getQuestion());
            }
            else if(note.getNote() >= MEDIUM_RANGE){
                if(!mediumRange.contains(note.getEvaluation()))
                    mediumRange.add(note);   
                mediumQuestion.add(note.getQuestion());
            }
            else{
                if(!lowRange.contains(note.getEvaluation()))
                    lowRange.add(note);
                lowQuestion.add(note.getQuestion());
            }
        }
        
        mapEval.put(HIGHT_RANGE, hightRange);
        mapEval.put(MEDIUM_RANGE, hightRange);
        mapEval.put(LOW_RANGE, hightRange);
        
    }
    
    private void setQuestionPrct(){
        
        prctQuestion = new HashMap<Question, Float>();
        
        for(Question q : questionList){
            prctQuestion.put(q, (float) 0);
        }
        
        for(Question q : questionList){
            for(Question aq : hightQuestion){
                if(aq.getIdQuestions() == q.getIdQuestions()){
                    float prct = prctQuestion.get(q);
                    prctQuestion.put(q,prct+1);
                }
            }
        }
    }
    
    private void setInterest(){
        Map<String,Float> auteurPrct = new HashMap<String, Float>();
        Map<String,Float> typePrct = new HashMap<String, Float>();
        
        //On récupére la question qui a reçu la meilleur note le plus grand nombre de fois
        //Cette question représente un interet porté par le lecteur
        
        Question bestQuestion = null;
        Float tmpMaxPrct = (float) -3000;
        
        for(Question q : questionList){
            float resPrct = prctQuestion.get(q);
            if(resPrct > tmpMaxPrct){
                bestQuestion = q;
                tmpMaxPrct = resPrct;
            }
        }
        
        //Ici best question est la question ayant le plus souvent reçu une bonne note
        //On récupére la liste des livres concernant cette question
        
        List<Book> bookForBestQuestion = getbookForBestQuestion(noteList,bestQuestion);
        
        List<String> auteurList = new ArrayList<String>();
        for(Book book : bookForBestQuestion){
            if(auteurPrct.containsKey(book.getAutor())){
                Float tmp = auteurPrct.get(book.getAutor());
                auteurPrct.put(book.getAutor(), tmp+1);
            }else{
                auteurList.add(book.getAutor());
                auteurPrct.put(book.getAutor(), (float)1);
            }
        }
        
        List<String> typeList = new ArrayList<String>();
        for(Book book : bookForBestQuestion){
            if(typePrct.containsKey(book.getType())){
                Float tmp = typePrct.get(book.getType());
                typePrct.put(book.getType(), tmp+1);
            }else{
                typeList.add(book.getAutor());
                typePrct.put(book.getType(), (float)1);
            }
        }
        
        //On récupére le meilleur Auteur et le meilleur Type

        getBestAutor(auteurList, auteurPrct);
        
        getBestType(auteurList, auteurPrct);
        
        //Ici on posséde l'auteur et le type le plus présent lorsque l'utilisateur 
        //a mis une bonne note a cette question max
        
    }
    
    private Book foundBook(String autor){
        
        boolean readed = false;
        
        for(Book b : bookList){
            if(b.getAutor().equals(autor)){
                readed = isReaded(noteList, b);
                if(!readed){
                    return b;
                } 
                readed = false;
            }
        }
        
        return bookList.get(0);
    }
    
    
    public Book matchFounding(){
       
        //On repartie les notes et les questions en categories
        setMapEvalAndQuestionRank();
        
        //On calcule le nombre de fois qu'une réponse à une question est bonne lorsque
        //Un livre à reçu une bonne note
        //Lorsque X met une bonne note dans Y pourcent des cas c'est a la question Q
        setQuestionPrct();
        
        //Lorsque X à répondu d'une bonne note a cette question l'auteur etait A dans Y pourcent des cas
        setInterest();
        
        //Pour le moment on cherche seulement un livre de même auteur
        //Lorsqu'il y a apprentissage il faut faire une proposition auteur/type
        //Ensuite attendre l'evaluation et déduire une influence préçise
        return foundBook(bestAutor);
    }

    private List<Book> getbookForBestQuestion(List<Notes> noteList, Question bestQuestion){
        List<Book> bookForBestQuestion = new ArrayList<Book>();
            for(Notes n : noteList){
                if(n.getQuestion().getIdQuestions() == bestQuestion.getIdQuestions()){
                    bookForBestQuestion.add(n.getEvaluation().getBook());
                }
            }
        return bookForBestQuestion;
    }
    
    private void getBestAutor(List<String> auteurList, Map<String,Float> auteurPrct) {
        bestAutor = "";
        Float tmpMaxAutor = (float) -3000;
        for(String autor:auteurList){
            float a = auteurPrct.get(autor);
            if(a>tmpMaxAutor){
                bestAutor = autor;
                tmpMaxAutor = a;
            }
        }
    }
    
    private void getBestType(List<String> auteurList, Map<String,Float> auteurPrct) {
        bestType = "";
        Float tmpMaxType = (float) -3000;
        
        for(String type:auteurList){
            float a = auteurPrct.get(type);
            if(a>tmpMaxType){
                bestAutor = type;
                tmpMaxType = a;
            }
        }
    }
    
    private boolean isReaded(List<Notes> noteList, Book b) {
        for(Notes n : noteList){
            if(n.getEvaluation().getBook().getIdBook() == b.getIdBook()){
                return true;
            }
        }
        return false;
    }
}
