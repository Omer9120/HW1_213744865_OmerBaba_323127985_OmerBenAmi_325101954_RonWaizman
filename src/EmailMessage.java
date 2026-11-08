import java.util.ArrayList;

public class EmailMessage extends Message implements IDigital {
    
    private String subject;
    private ArrayList<File> attachments;

    public EmailMessage(String sender, String content, int classificationLevel, String subject) {
        super(sender, content, classificationLevel);
        this.subject = subject;
        this.attachments = new ArrayList<>();
    }

    public void addAttachment(File file) {
        if (file != null) {
            attachments.add(file);
        }
    }

    public void removeAttachment(File file) throws AttachmentException {
        if (!attachments.remove(file)) {
            throw new AttachmentException("Error: Attachment not found!");
        }
    }

    @Override
    public String generatePreview() {
        return "[Email] Subject: " + subject;
    }

    @Override
    public String printCommunicationMethod() {
        return "This message is sent via the secure Email network.";
    }

    @Override
    public String toString() {
        String output = super.toString() + "\nSubject: " + subject + "\nAttachments:";
        if (attachments.isEmpty()) {
            output += " None";
        } else {
            for (File f : attachments) {
                output += "\n - " + f.toString();
            }
        }
        return output;
    }
}