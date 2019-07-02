import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

public class DocAdapter
{
    HashMap<String, String> nullify = new HashMap<>();

    HashMap<String, String> physio = new HashMap<>();

    HashMap<String, String> neuro = new HashMap<>();

    HashMap<String, String> derma = new HashMap<>();

    HashMap<String, String> cardio = new HashMap<>();

    HashMap<String, String> gastro = new HashMap<>();

    HashMap<String, String> nephro = new HashMap<>();

    public void addDoc()
    {

        physio.put("Dejavu", "8:30-11:00"); physio.put("Jack", "8:30-12:00"); physio.put("Ramu", "9:00-11:00"); physio.put("Chan", "9:30-12:00");

        neuro.put("Asfaq", "9:00-10:00"); neuro.put("Sagar", "11:00-1:00"); neuro.put("Reddy", "10:30-1:00"); neuro.put("Bios", "11:00-12:00");

        derma.put("Afsar", "1:30-3:00"); derma.put("kiran", "1:30-3:30"); derma.put("krishna", "11:30-1:30"); derma.put("Nikhil", "4:00-6:00");

        cardio.put("Tarun", "10:30-1:00"); cardio.put("Sarpa", "11:30-2:30"); cardio.put("Tiger", "10:00-11:00"); cardio.put("Pranay", "8:30-12:00");

        gastro.put("Smith", "4:30-7:00"); gastro.put("Warne", "2:30-4:00"); gastro.put("Sandeep", "2:30-4:00"); gastro.put("Sai", "2:30-5:00");

        nephro.put("Nilopher", "10:30-1:30"); nephro.put("Willsmith", "8:30-11:00"); nephro.put("Jack ma", "1:30-4:30"); nephro.put("Dellotius", "4:30-6:00");
    }

    public HashMap<String, String> displayDoc(int ch)
    {
        switch(ch)
        {
            case 1: return physio;

            case 2: return neuro;

            case 3: return derma;

            case 4: return cardio;

            case 5: return gastro;

            case 6: return nephro;

            default : ;
        }
        return nullify;

    }


}