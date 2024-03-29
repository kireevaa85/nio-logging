package ru.kireev.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sergey
 * created on 24.09.18.
 */
public class XMLhandler extends DefaultHandler {
    private final List<Share> result = new ArrayList<>();
    private Share share;

    private final String SHARES = "shares";
    private final String SHARE  = "share";
    private final String TICKER = "ticker";
    private final String LAST   = "last";
    private final String DATE   = "date";

    private boolean sharesFlag = false;
    private boolean shareFlag  = false;
    private boolean tickerFlag = false;
    private boolean lastFlag   = false;
    private boolean dateFlag   = false;

    @Override
    public void startElement(String uri, String localName, String qName,  Attributes attributes) {
        System.out.println("Start Element :" + qName);

        if (SHARES.equals(qName)) {
            sharesFlag = true;
            return;
        }

        if (sharesFlag) {
            if (SHARE.equals(qName)) {
                shareFlag = true;
                share = new Share();
                return;
            }
        }

        if (shareFlag) {
            switch(qName) {
                case TICKER:
                    tickerFlag = true;
                    break;
                case LAST:
                    lastFlag = true;
                    break;
                case DATE:
                    dateFlag = true;
                    break;
                default:
                    throw new IllegalArgumentException("unknown name:" + qName);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)  {
        System.out.println("End Element :" + qName);

        switch(qName) {
            case SHARES:
                sharesFlag = false;
                break;
            case SHARE:
                shareFlag = false;
                result.add(share);
                share = null;
                break;
            case TICKER:
                tickerFlag = false;
                break;
            case LAST:
                lastFlag = false;
                break;
            case DATE:
                dateFlag = false;
                break;
            default:
                throw new IllegalArgumentException("unknown name:" + qName);
        }

    }

    @Override
    public void characters(char ch[], int start, int length) {
        String value = new String(ch, start, length);

        if (tickerFlag) {
            System.out.println("ticker: "  + value);
            share.setTicker(value);
        }

        if (lastFlag) {
            System.out.println("last: "  + value);
            share.setLast(Double.valueOf(value));
        }

        if (dateFlag) {
            System.out.println("date: "  + value);
            share.setDate(value);
        }
    }

    public List<Share> getResult() {
        return result;
    }
}
