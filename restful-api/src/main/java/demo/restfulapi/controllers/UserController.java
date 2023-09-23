package demo.restfulapi.controllers;

import demo.restfulapi.entities.User;
import demo.restfulapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path="/api/get-users")
    public List<User> listUsers(@RequestParam(name="limit", required = false) Integer limit) {
        return (limit == null)
            ? userService.getListUsers()
            : userService.getListUsers(limit);
    }

    @GetMapping(path="/api/get-user-data/{userId}")
    public User getUser(@PathVariable(name = "userId") Long userId) {
        return userService.getUser(userId);
    }

    /***
     * Add new user
     * @return
     */
    @PostMapping(path="/api/add-user")
    public String addUser() {
        return userService.addUser();
    }

    /***
     * Add user with request JSON data
     * @param newUser
     * @return
     */
    @PostMapping(path="/api/add-custom-user")
    public String addCustomUser(@RequestBody User newUser) {
        return userService.addCustomUser(newUser.getUserId(), newUser.getName(), newUser.getAge());
    }

    /***
     * Update user with request JSON data
     * @param userId
     * @param updateUser
     * @return
     */
    @PutMapping(path="/api/update-user/{userId}")
    public String updateUser(@PathVariable(name="userId") Long userId, @RequestBody User updateUser) {
        return userService.updateUser(userId, updateUser.getName(), updateUser.getAge());
    }

    /***
     * Delete user with userId
     * @param userId
     * @return
     */
    @DeleteMapping(path="/api/delete-user/{userId}")
    public String deleteUser(@PathVariable(name="userId") Long userId) {
        return userService.deleteUser(userId);
    }




}
