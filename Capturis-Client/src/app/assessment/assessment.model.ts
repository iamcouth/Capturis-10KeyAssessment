export class Assessment
{
  public assessmentId: number;
  public userId: number;
  public dateTaken: Date;
  public timeGiven: number;
  public typeId: number;
  public keystrokes: number;
  public backspaces: number;
  public inputValues:string[];
  public expectedValues:string[];
  public enterCount: number;
}
