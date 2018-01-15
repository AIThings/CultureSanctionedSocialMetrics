package yaes.cssm.util.webgui.httpserver;

import java.util.AbstractMap.SimpleEntry;
import java.util.Scanner;

import yaes.ui.format.Formatter;


/**
 * A query object which is used to query Xapagy
 * 
 * @author Lotzi Boloni
 * 
 */
public class GsQuery {

    /**
     * The result type - either a web page, or an image
     */
    public enum ResultType {
        DOT, HTML, JPG, PDF, EPS
    }

    public enum SortingKey {
        SORTED_BY_CONTINUATION, SORTED_BY_MOOD_SCORE, SORTED_BY_DEPENDENT_SCORE,
        SORTED_BY_STRENGTH, SORTED_BY_ID
    }

    private static final String Q_AGENT_NAME = "agentName";
    private static final String Q_AUTO_REFRESH = "autoRefresh";
    private static final String Q_CURSOR_FROM = "cursorFrom";
    private static final String Q_CURSOR_SIZE = "cursorSize";
    private static final String Q_CURSOR_TO = "cursorTo";
    private static final String Q_CURSOR_TOTAL = "cursorTotal";
    private static final String Q_FILTER_VALUE = "filterValue";
    private static final String Q_FILTERED_BY = "filteredBy";
    private static final String Q_ID = "id";
    private static final String Q_QUERY_TYPE = "queryType";

    private static final String Q_RESULT_TYPE = "resultType";

    private static final String Q_SORTED_BY = "sortedBy";

    /**
     * Parses a query string as arriving from a web server
     * 
     * It is the format ?attr=name&...
     * 
     */
    public static GsQuery parseQueryString(String query) {
        if (!query.startsWith("?")) {
            throw new Error("Query should start with ?:" + query);
        }
        GsQuery gsQuery = new GsQuery();
        String temp = query.substring(1);
        try (Scanner scanner = new Scanner(temp)) {
            scanner.useDelimiter("&");
            while (scanner.hasNext()) {
                String item = scanner.next();
                int eqloc = item.indexOf("=");
                if (eqloc == -1) {
                    throw new Error("invalid item:" + item);
                }
                String name = item.substring(0, eqloc);
                String value = item.substring(eqloc + 1, item.length());
                switch (name) {
                case Q_AGENT_NAME:
                    gsQuery.agentName = value;
                    break;
                case Q_ID:
                    gsQuery.id = value;
                    break;
                case Q_RESULT_TYPE:
                    gsQuery.resultType = ResultType.valueOf(value);
                    break;
                case Q_QUERY_TYPE:
                    gsQuery.queryType = QueryType.valueOf(value);
                    break;
                case Q_SORTED_BY:
                    gsQuery.sortedBy = value;
                    break;
                case Q_FILTERED_BY:
                    gsQuery.filteredBy = value;
                    break;
                case Q_FILTER_VALUE:
                    gsQuery.filterValue = value;
                    break;
                case Q_AUTO_REFRESH:
                    gsQuery.autoRefresh = Boolean.parseBoolean(value);
                    break;
                case Q_CURSOR_FROM:
                    gsQuery.cursorFrom = Integer.parseInt(value);
                    break;
                case Q_CURSOR_TO:
                    gsQuery.cursorTo = Integer.parseInt(value);
                    break;
                case Q_CURSOR_SIZE:
                    gsQuery.cursorSize = Integer.parseInt(value);
                    break;
                case Q_CURSOR_TOTAL:
                    gsQuery.cursorTotal = Integer.parseInt(value);
                    break;
                default:
                    throw new Error("Unsupported parameter " + name);
                }
            }
            scanner.close();
        }
        return gsQuery;
    }

    /**
     * The name of the agent to which the query refers to
     */
    private String agentName = "";
    /**
     * Private boolean refresh
     */
    private boolean autoRefresh = false;
    /**
     * The from count of the cursor
     */
    private int cursorFrom = 0;
    /**
     * The size of the cursor (this can be larger than the to-from)
     */
    private int cursorSize = 0;
    /**
     * The to count of the cursor
     */
    private int cursorTo = 0;
    /**
     * The total number of items of the database underlying the cursor
     */
    private int cursorTotal = 0;
    /**
     * Filtered by
     */
    private String filteredBy = "";
    /**
     * Filter value
     */
    private String filterValue = "";
    /**
     * The identifier of the XapagyComponent to which the query refers to
     */
    private String id = "";
    /**
     * The query type
     */
    private QueryType queryType = QueryType.SCENARIO_SET;
    /**
     * The data format of the result
     */
    private ResultType resultType = ResultType.HTML;
    /**
     * A property based on which the query results will be sorted
     */
    private String sortedBy = "";

    public GsQuery() {
    }

    /**
     * Copy constructor
     * 
     * @param agent
     */
    public GsQuery(GsQuery other) {
        this.agentName = other.agentName;
        this.id = other.id;
        this.queryType = other.queryType;
        this.resultType = other.resultType;
        this.sortedBy = other.sortedBy;
        this.cursorFrom = other.cursorFrom;
        this.cursorTo = other.cursorTo;
        this.cursorSize = other.cursorSize;
        this.cursorTotal = other.cursorTotal;
        this.filteredBy = other.filteredBy;
        this.filterValue = other.filterValue;
        this.autoRefresh = other.autoRefresh;
    }

    /**
     * @return the agentName
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * @return the cursorFrom
     */
    public int getCursorFrom() {
        return cursorFrom;
    }

    /**
     * @return the cursorSize
     */
    public int getCursorSize() {
        return cursorSize;
    }

    /**
     * @return the cursorTo
     */
    public int getCursorTo() {
        return cursorTo;
    }

    /**
     * @return the cursorTotal
     */
    public int getCursorTotal() {
        return cursorTotal;
    }

    /**
     * @return the filteredBy
     */
    public String getFilteredBy() {
        return filteredBy;
    }

    /**
     * @return the filterValue
     */
    public String getFilterValue() {
        return filterValue;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the queryType
     */
    public QueryType getQueryType() {
        return queryType;
    }

    /**
     * @return the resultType
     */
    public ResultType getResultType() {
        return resultType;
    }

    /**
     * @return the sortedBy
     */
    public String getSortedBy() {
        return sortedBy;
    }

    /**
     * @return the refresh
     */
    public boolean isAutoRefresh() {
        return autoRefresh;
    }

    /**
     * @param agentName
     *            the agentName to set
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * @param autoRefresh
     *            the refresh to set
     */
    public void setAutoRefresh(boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    /**
     * @param cursorFrom
     *            the cursorFrom to set
     */
    public void setCursorFrom(int cursorFrom) {
        this.cursorFrom = cursorFrom;
    }

    /**
     * @param cursorSize
     *            the cursorSize to set
     */
    public void setCursorSize(int cursorSize) {
        this.cursorSize = cursorSize;
    }

    /**
     * @param cursorTo
     *            the cursorTo to set
     */
    public void setCursorTo(int cursorTo) {
        this.cursorTo = cursorTo;
    }

    /**
     * @param cursorTotal
     *            the cursorTotal to set
     */
    public void setCursorTotal(int cursorTotal) {
        this.cursorTotal = cursorTotal;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param queryType
     *            the queryType to set
     */
    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    /**
     * @param resultType
     *            the resultType to set
     */
    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public void setSortedBy(SortingKey key) {
        setSortedBy(key.toString());
    }

    /**
     * @param sortedBy
     *            the sortedBy to set
     */
    public void setSortedBy(String sortedBy) {
        this.sortedBy = sortedBy;
    }

    /**
     * Formats the GsQuery back to the format of the query
     * 
     * @return
     */
    public String toQuery() {
        StringBuffer buf = new StringBuffer("?");
        buf.append(GsQuery.Q_RESULT_TYPE + "=" + resultType + "&");
        buf.append(GsQuery.Q_QUERY_TYPE + "=" + queryType + "&");
        buf.append(GsQuery.Q_AUTO_REFRESH + "=" + autoRefresh + "&");
        buf.append("agentName" + "=" + agentName + "&");
        if (!id.equals("")) {
            buf.append("id=" + id + "&");
        }
        // only generate the cursors if you have a sorted-by
        if (!sortedBy.equals("")) {
            buf.append(GsQuery.Q_SORTED_BY + "=" + sortedBy + "&");
            buf.append(GsQuery.Q_CURSOR_FROM + "=" + cursorFrom + "&");
            buf.append(GsQuery.Q_CURSOR_TO + "=" + cursorTo + "&");
            buf.append(GsQuery.Q_CURSOR_SIZE + "=" + cursorSize + "&");
            buf.append(GsQuery.Q_CURSOR_TOTAL + "=" + cursorTotal + "&");
        }
        if (!filteredBy.equals("")) {
            buf.append(GsQuery.Q_FILTERED_BY + "=" + filteredBy + "&");
            buf.append(GsQuery.Q_FILTER_VALUE + "=" + filterValue + "&");
        }
        return buf.toString();
    }

    /**
     * Formats the query for human reading
     */
    @Override
    public String toString() {
        Formatter fmt = new Formatter();
        fmt.add("GsQuery");
        fmt.indent();
        fmt.is(GsQuery.Q_RESULT_TYPE, resultType);
        fmt.is(GsQuery.Q_QUERY_TYPE, queryType);
        fmt.is(GsQuery.Q_AGENT_NAME, agentName);
        fmt.is(GsQuery.Q_ID, id);
        fmt.is(GsQuery.Q_SORTED_BY, sortedBy);
        fmt.is(GsQuery.Q_FILTERED_BY, filteredBy);
        fmt.is(GsQuery.Q_FILTER_VALUE, filterValue);
        fmt.is(GsQuery.Q_CURSOR_FROM, cursorFrom);
        fmt.is(GsQuery.Q_CURSOR_TO, cursorTo);
        fmt.is(GsQuery.Q_CURSOR_SIZE, cursorSize);
        fmt.is(GsQuery.Q_CURSOR_TOTAL, cursorTotal);
        return fmt.toString();
    }

    
    /**
     * Creates the cursor neighbors - the previous and the other one, returned as 
     * a [previous, next] pair
     * 
     * If we are at the beginning, and can't move backward, the previous will be null.
     * 
     * If we are at the end, and can't move forward, the next will be null
     * 
     * @param gq
     * @return
     */
    public static SimpleEntry<GsQuery, GsQuery> createCursorNeighbors(GsQuery gq) {
        GsQuery gqPrevious = null;
        GsQuery gqNext = null;
        //
        // create the previous cursor
        //
        if (gq.getCursorFrom() > 0) {
            gqPrevious = new GsQuery(gq);
            int previousCursorFrom = gq.getCursorFrom() - gq.getCursorSize();
            if (previousCursorFrom < 0) {
                previousCursorFrom = 0;
            }
            int previousCursorTo = previousCursorFrom + gq.getCursorSize();
            if (previousCursorTo > gq.getCursorTotal()) {
                previousCursorTo = gq.getCursorTotal(); 
            }
            gqPrevious.setCursorFrom(previousCursorFrom);
            gqPrevious.setCursorTo(previousCursorTo);
        }
        //
        // create the current version
        //
        //
        //  link to the next cursor
        //
        if (gq.getCursorTo() < gq.getCursorTotal()) {
            gqNext = new GsQuery(gq);
            int nextCursorTo = gq.getCursorTo() + gq.getCursorSize();
            if (nextCursorTo > gq.getCursorTotal()) {
                nextCursorTo = gq.getCursorTotal(); 
            }
            int nextCursorFrom = nextCursorTo - gq.getCursorSize();
            if (nextCursorFrom < 0) {
                nextCursorFrom = 0;
            }
            gqNext.setCursorFrom(nextCursorFrom);
            gqNext.setCursorTo(nextCursorTo);            
        }
        
        return new SimpleEntry<>(gqPrevious, gqNext);
    }
    
}
