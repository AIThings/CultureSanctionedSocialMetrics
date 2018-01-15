package yaes.cssm.util.webgui.prettyhtml;

import yaes.ui.format.Formatter;
import yaes.ui.format.HtmlFormatter;
import yaes.ui.text.TextUi;


public class PwHelper {

    public static final String CLASS_BODYLINK = "bodylink";
    private static final String CLASS_EMBED = "embed";
    private static final String CLASS_ENUM = "enum";
    private static final String CLASS_EXPLANATION = "explanation";
    public static final String CLASS_GRAYOUT = "grayout";
    private static final String CLASS_INDENT = "indent";
    public static final String CLASS_LABEL = "label";
    static final String CLASS_NAVBAR = "navbar";
    private static final String CLASS_NOTIFICATION = "notification";
    public static final String CLASS_IDENTIFIER = "identifier";

    private static final String CLASS_METER_WRAP = "meter-wrap";
    private static final String CLASS_METER_VALUE = "meter-value";
    private static final String CLASS_METER_TEXT = "meter-text";
    private static final String CLASS_METER_WRAP_NEGATIVE =
            "meter-wrap-negative";
    private static final String CLASS_METER_VALUE_NEGATIVE =
            "meter-value-negative";

    /**
     * Adds an arrow
     * 
     * @param fmt
     */
    public static String addArrow(HtmlFormatter fmt) {
        fmt.add("<span style=\"font-size:200%;\">&rarr;</span>");
        return fmt.toString();
    }

        
    /**
     * A bolded element
     * 
     * @param fmt
     * @param label
     * @param values
     */
    public static String addBold(HtmlFormatter fmt, String label) {
        /*
         * fmt.openSpan("class=" + CLASS_LABEL); fmt.add(label);
         * fmt.closeSpan();
         */
        fmt.add("<b>" + label + "</b>");
        return fmt.toString();
    }

    /**
     * Adds an enum (monospaced) for types etc
     */
    public static String addEnum(HtmlFormatter fmt, String text) {
        fmt.openSpan("class=" + PwHelper.CLASS_ENUM);
        fmt.add(text);
        fmt.closeSpan();
        return fmt.toString();
    }

    /***
     * Adds an error message
     * 
     * @param notificationString
     */
    public static void addErrorMessage(HtmlFormatter fmt,
            String notificationString) {
        fmt.addPre(notificationString, "class=" + PwHelper.CLASS_NOTIFICATION);
    }

    /**
     * Adds an extensible component where the component is initially invisible
     * 
     * @param fmt
     * @param id
     * @param showString
     * @param hideString
     * @param content
     */
    public static String addExtensible(HtmlFormatter fmt, String id,
            String showString, String hideString, String content,
            boolean initiallyVisible) {
        // the show link
        if (initiallyVisible) {
            fmt.openA("href=\"#\"", "id=\"" + id + "-show\"", "class=\"more\"",
                    "onclick=\"showHide('" + id + "');return false;\"",
                    "style=\"display:none;\"");
        } else {
            fmt.openA("href=\"#\"", "id=\"" + id + "-show\"",
                    "onclick=\"showHide('" + id + "');return false;\"");
        }
        fmt.add(showString);
        fmt.closeA();
        // the show / hide content
        if (initiallyVisible) {
            fmt.openDiv("id=\"" + id + "\"");
        } else {
            fmt.openDiv("class=\"more\"", "id=\"" + id + "\"");
        }
        // the hide link
        fmt.openA("href=\"#\"", "id=\"" + id + "-hide\"",
                "onclick=\"showHide('" + id + "');return false;\"");
        fmt.add(hideString);
        fmt.closeA();
        // a little hack to avoid indenting preformatted data
        if (content.contains("<pre")) {
            fmt.addNonIndented(content);
        } else {
            fmt.addIndented(content);
        }
        fmt.closeDiv();
        return fmt.toString();
    }

    /**
     * A version of the extensible where the extensible is inside
     * 
     * @param fmt
     * @param id
     * @param showString
     * @param hideString
     * @param content
     */
    public static String addExtensibleH2(HtmlFormatter fmt, String id,
            String label, String content, boolean initiallyVisible) {
        String showString =
                "<h2 class=extensible>&gt;&gt;&gt; " + label + "</h2>";
        String hideString =
                "<h2 class=extensible>&lt;&lt;&lt; " + label + "</h2>";
        PwHelper.addExtensible(fmt, id, showString, hideString, content,
                initiallyVisible);
        return fmt.toString();
    }

    /**
     * Add a H3 header which is extensible
     * @param fmt
     * @param id
     * @param label
     * @param content
     * @param initiallyVisible
     * @return
     */
    public static String addExtensibleH3(HtmlFormatter fmt, String id,
            String label, String content, boolean initiallyVisible) {
        String showString =
                "<h3 class=extensible>&gt;&gt;&gt; " + label + "</h3>";
        String hideString =
                "<h3 class=extensible>&lt;&lt;&lt; " + label + "</h3>";
        PwHelper.addExtensible(fmt, id, showString, hideString, content,
                initiallyVisible);
        return fmt.toString();
    }

    
    /**
     * Adds an identifier
     */
    public static String addIdentifier(HtmlFormatter fmt, String identifier) {
        fmt.openSpan("class=" + PwHelper.CLASS_IDENTIFIER);
        fmt.add(identifier);
        fmt.closeSpan();
        return fmt.toString();
    }


    /**
     * Frequently found element: a label, followed by a value (or values)
     * 
     * @param fmt
     * @param label
     * @param values
     */
    public static String addLabelParagraph(HtmlFormatter fmt, String label,
            String... values) {
        fmt.openP();
        fmt.openSpan("class=" + PwHelper.CLASS_LABEL);
        fmt.add(label);
        fmt.closeSpan();
        for (String value : values) {
            fmt.add(value);
        }
        fmt.closeP();
        return fmt.toString();
    }

    /**
     * Ends an indented part
     */
    public static String deindent(HtmlFormatter fmt) {
        fmt.closeDiv();
        return fmt.toString();
    }

    /**
     * Ends an embedded part
     */
    public static String endEmbed(HtmlFormatter fmt) {
        fmt.closeDiv();
        return fmt.toString();
    }

    /**
     * Ends an embedded part
     */
    public static String endEmbedX(HtmlFormatter fmt) {
        fmt.closeTD();
        fmt.closeTR();
        fmt.closeTable();
        fmt.closeDiv();
        return fmt.toString();
    }

    /**
     * Adds an explanatory note. Frequently describes the format of the
     * following code.
     * 
     * @param explanation
     */
    public static String explanatoryNote(HtmlFormatter fmt, String explanation) {
        fmt.openP("class=" + PwHelper.CLASS_EXPLANATION);
        fmt.add(explanation);
        fmt.closeP();
        return fmt.toString();
    }

    /**
     * Ends a grayout
     */
    public static String grayoutEnd(HtmlFormatter fmt) {
        fmt.closeSpan();
        return fmt.toString();
    }

    /**
     * Starts a grayout
     */
    public static String grayoutStart(HtmlFormatter fmt) {
        fmt.openSpan("class=" + PwHelper.CLASS_GRAYOUT);
        return fmt.toString();
    }

    /**
     * Starts an indented part
     */
    public static String indent(HtmlFormatter fmt) {
        fmt.openDiv("class=" + PwHelper.CLASS_INDENT);
        return fmt.toString();
    }

    /**
     * Formats a name / value pair
     * 
     * @param name
     * @param object
     */
    public static String is(HtmlFormatter fmt, String name, Object object) {
        String value;
        if (object == null) {
            value = "<null>";
        } else {
            if (object instanceof Double) {
                value = Formatter.fmt((Double) object);
            } else {
                value = object.toString();
            }
        }
        return PwHelper.addLabelParagraph(fmt, name, " = " + value);
    }

    /**
     * Keeping the old one, which was working fine as a fallback
     * 
     * @param fmt
     * @param value
     * @param maxValue
     */
    public static String progressBarOld(HtmlFormatter fmt, double value,
            double maxValue) {
        try {
            if (maxValue <= 0) {
                throw new Error("Max value is smaller than zero!!!");
            }
            int percent = (int) (100 * value / maxValue);
            if (value >= 0 && value <= maxValue) {
                fmt.openSpan("class=\"meter-wrap\"");
                fmt.openSpan("class=\"meter-value\"", "style=\"width: "
                        + percent + "%;\"");
                fmt.openSpan("class=\"meter-text\"");
            }
            // smaller than zero: turn it pink
            if (value < 0) {
                percent = 0;
                fmt.openSpan("class=\"meter-wrap\"",
                        "style=\"background: #F665AB\""); // pink
                fmt.openSpan("class=\"meter-value\"", "style=\"width: "
                        + percent + "%;\"");
                fmt.openSpan("class=\"meter-text\"");
            }
            // larger than maxvalue: turn it red
            if (value > maxValue) {
                percent = 100;
                fmt.openSpan("class=\"meter-wrap\"");
                fmt.openSpan("class=\"meter-value\"", "style=\"width: "
                        + percent + "%; background: red;\"");
                fmt.openSpan("class=\"meter-text\"");
            }
            fmt.add(Formatter.fmt(value));
            fmt.closeSpan();
            fmt.closeSpan();
            fmt.closeSpan();
            return fmt.toString();
        } catch (Error ex) {
            TextUi.println(ex);
        }
        return "xxx";
    }

    /**
     * Shows a progress bar (implemented with span components)
     * 
     * If it is a positive value, it shows dark green on light green.
     * 
     * If it is a negative value, it shows dark red on pink
     * 
     * @param fmt
     * @param value
     * @param maxValue
     */
    public static String progressBar(HtmlFormatter fmt, double value,
            double maxValue) {
        String meterWrap = null;
        String meterValue = null;
        String meterText = CLASS_METER_TEXT;
        try {
            if (maxValue == 0) {
                throw new Error("Max value is zero!!!");
            }
            if (maxValue > 0) {
                meterWrap = CLASS_METER_WRAP;
                meterValue = CLASS_METER_VALUE;
            } else {
                meterWrap = CLASS_METER_WRAP_NEGATIVE;
                meterValue = CLASS_METER_VALUE_NEGATIVE;
            }
            int percent = (int) (100 * value / maxValue);
            if (percent >= 0 && percent <= 100) {
                fmt.openSpan("class=\"" + meterWrap + "\"");
                fmt.openSpan("class=\"" + meterValue + "\"", "style=\"width: "
                        + percent + "%;\"");
                fmt.openSpan("class=\"" + meterText + "\"");
            }
            //
            // FIXME: this color turning is not good for negative values
            // smaller than zero: turn it pink
            if (percent < 0) {
                percent = 0;
                fmt.openSpan("class=\"" + meterWrap + "\"",
                        "style=\"background: #F665AB\""); // pink
                fmt.openSpan("class=\"" + meterValue + "\"", "style=\"width: "
                        + percent + "%;\"");
                fmt.openSpan("class=\"" + meterText + "\"");
            }
            // larger than maxvalue: turn it red
            if (percent > 100) {
                percent = 100;
                fmt.openSpan("class=\"" + meterWrap + "\"");
                fmt.openSpan("class=\"" + meterValue + "\"", "style=\"width: "
                        + percent + "%; background: red;\"");
                fmt.openSpan("class=\"" + meterText + "\"");
            }
            if (value >= 0) {
                fmt.add(Formatter.fmt(value));
            } else {
                String text = Formatter.fmt(value); 
                fmt.add(text);
            }
            fmt.closeSpan();
            fmt.closeSpan();
            fmt.closeSpan();
            return fmt.toString();
        } catch (Error ex) {
            TextUi.println(ex);
        }
        return "xxx";
    }

    /**
     * Starts an embedded part
     */
    public static String startEmbed(HtmlFormatter fmt) {
        fmt.openDiv("class=" + PwHelper.CLASS_EMBED);
        return fmt.toString();
    }

    /**
     * Starts an embedded part - with a vertical label
     */
    public static String startEmbedX(HtmlFormatter fmt, String text) {
        fmt.openDiv("class=" + PwHelper.CLASS_EMBED);
        fmt.openTable("class=embedtable");
        fmt.openTR();
        fmt.openTD("class=embedvertical");
        // vertical text here
        fmt.openDiv("class=verticaltext");
        fmt.add(text);
        fmt.closeDiv();
        fmt.closeTD();
        fmt.openTD();
        return fmt.toString();
    }

}
