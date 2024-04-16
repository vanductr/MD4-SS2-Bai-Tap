package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.Blog;
import rikkei.academy.service.IBlogService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @GetMapping(value = {"/", "/blog"})
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("/blog/home");
        List<Blog> blogList = blogService.findAll();
        modelAndView.addObject("blogs", blogList);
        return modelAndView;
    }

    @GetMapping("/create-blog")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }
    @PostMapping("/create-blog")
    public ModelAndView create(@ModelAttribute("blog") Blog blog) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        blog.setCreatedDate(new Date());
        blogService.save(blog);
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable(value = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/blog/detail");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @GetMapping("edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        Blog blog = blogService.findById(id);
        Date date = blog.getCreatedDate();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateString = formatter.format(date);
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("date", dateString);
        return modelAndView;
    }
    @PostMapping("edit")
    public ModelAndView edit(@ModelAttribute("blog") Blog blog, @ModelAttribute("createdDate") String date) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            Date date2 = formatter.parse(date);
            blog.setCreatedDate(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        blogService.save(blog);
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/blog/delete");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @PostMapping("delete")
    public ModelAndView delete(@ModelAttribute("blog") Blog blog) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        blogService.remove(blog.getId());
        return modelAndView;
    }
}
