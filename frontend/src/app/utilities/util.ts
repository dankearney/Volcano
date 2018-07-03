export abstract class Util {

    //  Get headers and auth token for making HTTP Requests
    public static getReqConfig() {
        const token = window.localStorage.getItem("authToken");
        const config = { headers:  {
          'Content-Type': 'application/json',
          'Authorization' : 'Basic ' + token
        } };
        return config;
    }

    // Gets the user principal
    public static getLoggedInUser() {
        const userPrincipalStr = window.localStorage.getItem("userPrincipal");
        if (userPrincipalStr == null || userPrincipalStr == "") {
            return null;
        }
        return JSON.parse(userPrincipalStr);
    }

    // Get logged in user's username
    public static getLoggedInUserName() {
        const userPrincipal = this.getLoggedInUser();
        if (userPrincipal != null)
        {
            return userPrincipal.username;        
        }
        else
        {
            return null;
        }
    }

    // Checks if user is logged in
    public static loggedIn() {
        return window.localStorage.getItem("authToken") == null;
    }

    public static logOut() {
        window.localStorage.setItem("authToken", "");
        window.localStorage.setItem("userPrincipal", "")
    }

    // Writes an error message
    public static writeError(error: string) {
        let message =`
          <div class="alert alert-warning alert-dismissible fade show" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
            <strong>Error: </strong>
        ` + error + "</div>";
        document.getElementById("systemOutput").innerHTML = message;
    }

    // Writes a success message
    public static writeSuccess(success: string) {
        let message =`
          <div class="alert alert-success alert-dismissible fade show" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
            <strong>Success: </strong>
        ` + success + "</div>";        
        document.getElementById("systemOutput").innerHTML = message;
    }

    // Writes a generic error message
    public static writeGenericError() {
        this.writeError("Something went wrong. Try logging in.");
    }

}