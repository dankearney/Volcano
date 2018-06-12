export class Chat {
  sender: string;
  thread: string;
  project: string;
  message: string;

  constructor (sender: string, message: string) { 
    this.sender = sender;
    this.message = message;
  }

}