
package history;

import java.io.Serializable;
import java.util.Date;

public class TimpData implements Serializable {

  private int ora;
  private int minute;
  private int secunde;
  private int anul;
  private int luna;
  private int ziua;
  private Date d;

  public TimpData(int ora, int minute, int secunde, int ziua, int luna, int anul) {
    this.ora = ora;
    this.minute = minute;
    this.secunde = secunde;
    this.ziua = ziua;
    this.luna = luna;
    this.anul = anul;
  }

  public TimpData() {
  }

  public String corectare(int i) {
    String s = i + "";
    String o = "0";
    if (i == 0)
      return o + s;
    if (i % 10 != 0 && i < 10)
      return o + s;
    else
      return s;
  }

  public static int daysInMonth(int month, int an) {

    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
      return 31;
    }
    if (month == 4 || month == 6 || month == 9 || month == 11) {
      return 30;
    }
    if (month == 2) {
      return ((an % 400 == 0) || (an % 4 == 0 && an % 100 != 0)) ? 29 : 28;
    }
    return 0;
  }

  public static int[] oraScursa(TimpData t1, TimpData t2) {

    int difOra = 0;
    int difMinute = 0;
    int difSecunde = 0;
    if (t1.ora != 0 && t2.ora != 0)
      difOra = t2.ora - t1.ora;
    else if (t1.ora == 0 && t2.ora != 0)
      difOra = t2.ora;
    else if (t1.ora != 0 && t2.ora == 0)
      difOra = 24 - t1.ora;
    if (difOra == 0)
      difMinute = t2.minute - t1.minute;
    else {
      difMinute = (60 - t1.minute) + t2.minute;
      difOra--;
    }
    if (difMinute > 59) {
      difMinute = difMinute % 60;
      difOra++;
    }
    if (difMinute == 0)
      difSecunde = t2.secunde - t1.secunde;
    else {
      difSecunde = (60 - t1.secunde) + t2.secunde;
      difMinute--;
    }
    if (difSecunde > 59) {
      difSecunde = difSecunde % 60;
      difMinute++;
    }
    int[] timp = new int[3];
    timp[0] = difOra;
    timp[1] = difMinute;
    timp[2] = difSecunde;
    return timp;
  }

  public static TimpData timpScurs(TimpData dateTime1, TimpData dateTime2) {

    if (dateTime1.anul > dateTime2.anul) {
      TimpData temp = dateTime2;
      dateTime2 = dateTime1;
      dateTime1 = temp;
    }
    int difYear = dateTime2.anul - dateTime1.anul;
    int difMonth = dateTime2.luna - dateTime1.luna;
    if (difMonth < 0) {
      difYear--;
      difMonth = 12 - (difMonth * (-1));
    }
    int difDay = dateTime2.ziua - dateTime1.ziua;
    if (difDay < 0) {
      difMonth--;
      if (difMonth < 0) {
        difMonth = 12 - (difMonth * (-1));
        difYear--;
      }
      difDay = daysInMonth(dateTime1.luna, dateTime1.anul) - (difDay * (-1));
    }
    int difHour = oraScursa(dateTime1, dateTime2)[0];
    int difMinutes = oraScursa(dateTime1, dateTime2)[1];
    int difSeconds = oraScursa(dateTime1, dateTime2)[2];

    //String s = "ore: " + difHour + ", minute: " + difMinutes + ", secunde: " + difSeconds + ", zile: " + difDay + ", luni: " + difMonth + ", ani: " + difYear + ";\n";
    return new TimpData(difHour, difMinutes, difSeconds, difDay, difMonth, difYear);

  }

  public int luna(String s) {
    switch (s) {
      case ("Jan"):
        return 1;
      case ("Feb"):
        return 2;
      case ("Mar"):
        return 3;
      case ("Apr"):
        return 4;
      case ("May"):
        return 5;
      case ("Jun"):
        return 6;
      case ("Jul"):
        return 7;
      case ("Aug"):
        return 8;
      case ("Sep"):
        return 9;
      case ("Oct"):
        return 10;
      case ("Nov"):
        return 11;
      case ("Dec"):
        return 12;
    }
    return 0;
  }

  //Wed Mar 14 00:12:19 EET 2018
  public TimpData timp() {

    d = new Date();
    String s = d.toString();
    String d1[] = s.split(" ");
    String dOra[] = d1[3].split(":");
    int ora = Integer.parseInt(dOra[0]);
    int minute = Integer.parseInt(dOra[1]);
    int secunde = Integer.parseInt(dOra[2]);
    int ziua = Integer.parseInt(d1[2]);
    int luna = luna(d1[1]);
    int anul = Integer.parseInt(d1[5]);
    TimpData t = new TimpData(ora, minute, secunde, ziua, luna, anul);
    return t;
  }

  public void setOra(int ora) {
    this.ora = ora;
  }

  public int getOra() {
    return this.ora;
  }

  public void setMinute(int minute) {
    this.minute = minute;
  }

  public int getMinute() {
    return this.minute;
  }

  public void setSecunde(int secunde) {
    this.secunde = secunde;
  }

  public int getSecunde() {
    return this.secunde;
  }

  public void setAnul(int anul) {
    this.anul = anul;
  }

  public int getAnul() {
    return this.anul;
  }

  public void setLuna(int luna) {
    this.luna = luna;
  }

  public int getLuna() {
    return this.luna;
  }

  public void setZiua(int ziua) {
    this.ziua = ziua;
  }

  public int getZiua() {
    return this.ziua;
  }

  @Override
  public boolean equals(Object d) {
    if (d instanceof Date) {
      TimpData td = (TimpData) d;
      if (this.ora == td.getOra() && this.minute == td.getMinute() && this.secunde == td.getSecunde() && this.ziua == td.getZiua() && this.luna == td.getLuna() && this.anul == td.getAnul())
        return true;
      else
        return false;
    } else
      return false;
  }

  @Override
  public String toString() {
    return " " + corectare(this.ziua) + "." + corectare(this.luna) + "." + this.anul + " ora " + corectare(this.ora) + " : " + corectare(this.minute) + " : " + corectare(this.secunde);
  }

}