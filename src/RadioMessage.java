public class RadioMessage extends Message {

    private double frequency;

    public RadioMessage(String sender, String content, int classificationLevel, double frequency) throws FrequencyOutOfRangeException {
        super(sender, content, classificationLevel);
        setFrequency(frequency);
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) throws FrequencyOutOfRangeException {
        if (frequency < 30.0 || frequency > 512.0) {
            throw new FrequencyOutOfRangeException("Error: Frequency must be between 30.0 and 512.0 MHz.");
        }
        this.frequency = frequency;
    }

    @Override
    public String generatePreview() {
        return "[RadioMessage] Frequency: " + frequency + " MHz";
    }

    @Override
    public String toString() {
        return super.toString() + 
               "\nFrequency: " + frequency + " MHz";
    }
}