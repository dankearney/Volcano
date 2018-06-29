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
        return JSON.parse(userPrincipalStr);
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