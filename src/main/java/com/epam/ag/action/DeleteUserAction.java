package com.epam.ag.action;

import com.epam.ag.model.User;
import com.epam.ag.service.UserService;
import com.epam.ag.utils.SystemMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Govorov Andrey.
 */
public class DeleteUserAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long userId;
        try {
            userId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("user.form.wrong.id", SystemMessage.ERROR));
            return "redirect:controller?action=user-list";
        }

        // If I try to delete myself
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            long currentUserId = user.getId();
            if (currentUserId == userId) {
                req.getSession().setAttribute("systemMessage", new SystemMessage("user.form.error.delete.yourself", SystemMessage.ERROR));
                return "redirect:controller?action=user-list";
            }
        }

        UserService service = new UserService();
        boolean isDeleted = service.deleteById(userId);
        if (!isDeleted) {
            req.getSession().setAttribute("systemMessage", new SystemMessage("user.form.delete.fail", SystemMessage.ERROR));
            return "redirect:controller?action=color-list";
        }
        req.getSession().setAttribute("systemMessage", new SystemMessage("user.delete.success", SystemMessage.SUCCESS));
        return "redirect:controller?action=user-list";
    }
}
