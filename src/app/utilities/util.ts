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

    public static hideSystemOutput() {
        document.getElementById("systemOutput").style.display = 'none';
    }

    public static showSystemOutput() {
        document.getElementById("systemOutput").style.display = 'block';
    }

    // Returns user's currently scoped team
    public static getCurrentTeam() {
        var teamStr = window.localStorage.getItem("team");
        if (teamStr == 'undefined') {
            teamStr = null;
        }
        return JSON.parse(teamStr);
    }

    // Returns current team ID, as a string
    public static getCurrentTeamId() {
        return Util.getCurrentTeam().teamId.toString();
    }

    // Sets a user's currently scoped team
    public static setCurrentTeam(team: Object) {
        window.localStorage.setItem("team", JSON.stringify(team))
    }

    // Checks if user is logged in
    public static loggedIn() {
        return window.localStorage.getItem("authToken") == null;
    }

    public static logOut() {
        window.localStorage.setItem("authToken", null);
        window.localStorage.setItem("userPrincipal", null)
        Util.logOutTeam();
    }

    public static logOutTeam() {
        window.localStorage.setItem("team", null);
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
        Util.showSystemOutput();
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
        Util.showSystemOutput();
    }

    // Writes a generic error message
    public static writeGenericError() {
        this.writeError("Something went wrong. Try logging in.");
    }

}