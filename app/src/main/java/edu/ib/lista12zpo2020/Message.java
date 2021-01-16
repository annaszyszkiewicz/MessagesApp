package edu.ib.lista12zpo2020;

/**
 * klasa pojedynczej wiadomosciw na liscie historii
 */
public class Message {

    private String filename;
    private String msg;

    /**
     * konstruktor wiaodmosci
     *
     * @param filename nazwa wiaodmosci
     * @param msg      tresc wiadomosci
     */
    public Message(String filename, String msg) {
        this.filename = filename;
        this.msg = msg;
    }

    /**
     * metoda zwracajaca nazwe wiaodmosci
     *
     * @return nazwa wiaodmosci
     */
    public String getFilename() {
        return filename;
    }

    /**
     * metoda zwracajaca tresc wiadomosci
     *
     * @return tresc wiadomosci
     */
    public String getMsg() {
        return msg;
    }

    /**
     * przeslonieta metoda
     *
     * @return nazwe i tresc wiadomosci
     */
    @Override
    public String toString() {
        return filename.split("\\.")[0] + "\n" + msg;
    }
}
