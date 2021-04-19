public class AuthController {
    UserRepository userRepository;
    AuthService authService;

    //won't be void
    public void login(String username, String password) {
        authService.performLogin();
    }
    //won't be void
    public void logout() {
        authService.performLogout();
    }
}
