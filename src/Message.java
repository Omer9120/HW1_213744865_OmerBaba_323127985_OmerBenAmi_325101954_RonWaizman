import java.util.ArrayList;
import java.util.Date;

public abstract class Message {
    protected String sender;
    protected String content;
    protected Date sendDate;
    protected int classificationLevel;

    public Message(String sender, String content, int classificationLevel) {
        this.sender = sender;
        this.content = content;
        this.classificationLevel = classificationLevel;
        this.sendDate = new Date(); 
    }

    public boolean find(ArrayList<String> words) {
        if (content == null || words == null) {
            return false;
        }
        
        for (String word : words) {
            if (content.contains(word)) {
                return true;
            }
        }
        return false;
    }

        public abstract String generatePreview();

    
    @Override
    public String toString() {
        return "Sender: " + sender + 
               "\nDate: " + sendDate + 
               "\nClassification Level: " + classificationLevel + 
               "\nContent: " + content;
    }
}