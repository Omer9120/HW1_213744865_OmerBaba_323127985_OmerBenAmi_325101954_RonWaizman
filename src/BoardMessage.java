public class BoardMessage extends Message implements IDigital {
    
    private PriorityType priority;
    private String teamName;

    public BoardMessage(String sender, String content, int classificationLevel, PriorityType priority, String teamName) {
        super(sender, content, classificationLevel);
        this.priority = priority;
        this.teamName = teamName;
    }

    @Override
    public String generatePreview() {
        return "[BoardMessage] To: " + teamName + " | Priority: " + priority;
    }

    @Override
    public String printCommunicationMethod() {
        return "This message is displayed on the digital board.";
    }

    @Override
    public String toString() {
        return super.toString() + 
               "\nPriority: " + priority + 
               "\nTeam: " + teamName;
    }
}
