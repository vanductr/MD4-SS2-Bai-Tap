package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.entity.Comment;
import rikkei.academy.service.ICommentService;

import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping(value = {"/", "/comment"})
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("/comment/home");
        List<Comment> commentList = commentService.findTodayComment();
        modelAndView.addObject("comments", commentList);
        return modelAndView;
    }

    @PostMapping("/add-new-comment")
    public ModelAndView andNewComment(@ModelAttribute("author") String author,
                                      @ModelAttribute("comment") String comment,
                                      @ModelAttribute("rating") int rating) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        Comment c = new Comment(null, author, comment, rating, 10, new Date());
        commentService.save(c);
        return modelAndView;
    }
}
