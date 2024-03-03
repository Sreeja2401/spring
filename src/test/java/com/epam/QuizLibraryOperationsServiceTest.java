package com.epam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.customexceptions.DuplicateQuizFoundException;
import com.epam.customexceptions.QuizNotFoundException;
import com.epam.db.QuizLibraryOperationsDao;
import com.epam.entity.QuestionsLibrary;
import com.epam.entity.QuizLibrary;
import com.epam.sl.QuizLibraryOperationsService;

@ExtendWith(MockitoExtension.class)
class QuizLibraryOperationsServiceTest {
    @Mock
    QuizLibraryOperationsDao quizLibraryOperationsDao;
    @InjectMocks
    QuizLibraryOperationsService quizLibraryOperationsService;

    List<QuizLibrary>quizList;
    QuizLibrary quiz1;
    QuizLibrary quiz2;
    @BeforeEach
       public void setUp()
        {
             quiz1=new QuizLibrary("colleges",65, Arrays.asList(
                    new QuestionsLibrary("vjit location", Arrays.asList("a","b","c"),"low","collage","a"),
                    new QuestionsLibrary("cbit location?", Arrays.asList("gandipet","b","c"),"low","collage","gandipet")
            ));
             quiz2=new QuizLibrary("vjit",65, Arrays.asList(
                    new QuestionsLibrary("vjit location", Arrays.asList("a","b","c"),"low","collage","a")
            ));
             quizList=new ArrayList<>();
            quizList.add(quiz1);
            quizList.add(quiz2);
        }

    @Test
     void addQuiz() throws DuplicateQuizFoundException {
       QuizLibrary quiz3=new QuizLibrary("cbit",65, Arrays.asList(
                new QuestionsLibrary("vjit location", Arrays.asList("a","b","c"),"low","collage","a")
        ));
        Mockito.when(quizLibraryOperationsDao.viewAll()).thenReturn(quizList);
        Mockito.when(quizLibraryOperationsDao.save(quiz3)).thenReturn(quiz3);
        QuizLibrary retrievedQuiz=quizLibraryOperationsService.addQuizz(quiz3);
        assertEquals(retrievedQuiz,quiz3);
    }
    @Test
     void addQuizWithDuplicateTitle() throws DuplicateQuizFoundException {
        QuizLibrary quiz3=new QuizLibrary("colleges",65, Arrays.asList(
                new QuestionsLibrary("vjit location", Arrays.asList("a","b","c"),"low","collage","a")
        ));
            Mockito.when(quizLibraryOperationsDao.viewAll()).thenReturn(quizList);
            try{
            QuizLibrary retrievedQuiz = quizLibraryOperationsService.addQuizz(quiz3);
            }
          catch(DuplicateQuizFoundException q)
          {
              assertEquals("quiz with given title already exist..... ",q.getMessage());
          }
    }

    @Test
    void viewAllQuizTest()
    {
        Mockito.when(quizLibraryOperationsDao.viewAll()).thenReturn(quizList);
        List<QuizLibrary>retrievedQuizList=quizLibraryOperationsService.viewQuiz();
        assertEquals(quizList,retrievedQuizList);
    }
    @Test
     void viewQuizByExistingDomainTest() throws QuizNotFoundException {
        Mockito.when(quizLibraryOperationsDao.viewAll()).thenReturn(quizList);
        List<QuizLibrary>retrievedQuiz=quizLibraryOperationsService.viewQuizByDomain("vjit");
        assertNotNull(retrievedQuiz);
    }
    @Test
     void viewQuizByNotExistingDomainTest() throws QuizNotFoundException {

        Mockito.when(quizLibraryOperationsDao.viewAll()).thenReturn(quizList);
        try {
            List<QuizLibrary> retrievedQuiz = quizLibraryOperationsService.viewQuizByDomain("java");
        }
        catch (QuizNotFoundException q)
        {
            assertEquals("quiz does not exist with given domain",q.getMessage());
        }
    }

    @Test
     void updateQuiz()
    {
        QuizLibrary quiz2Updated=new QuizLibrary("vjitLocation",65, Arrays.asList(
                new QuestionsLibrary("vjit location", Arrays.asList("a","b","c"),"low","collage","a")
        ));
        Mockito.when(quizLibraryOperationsDao.viewAll()).thenReturn(quizList);
        Mockito.when(quizLibraryOperationsDao.update(quiz1)).thenReturn(quiz2Updated);
        Boolean result=quizLibraryOperationsService.editQuiz("vjit","vjitLocation");
        assertTrue(result);
    }
    @Test
     void deleteQuiz()
    {
        quiz2.setId(89);
        Mockito.when(quizLibraryOperationsDao.viewAll()).thenReturn(quizList);
        Mockito.when(quizLibraryOperationsDao.delete(89)).thenReturn(true);
        boolean result=quizLibraryOperationsService.deleteQuiz("vjit");
        assertTrue(result);
    }

}
