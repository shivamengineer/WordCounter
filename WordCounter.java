import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author S. Engineer
 */
public final class WordCounter {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private WordCounter() {
        // no code needed here
    }

    /**
     *
     * @param sortedKeys
     * @param words
     * @param outputFile
     */
    public static void printJS(Sequence<String> sortedKeys,
            Map<String, Integer> words, SimpleWriter outputFile) {
        outputFile.print("<canvas id=canvas width=1200 height=600 style=\"bor");
        outputFile.println("der:1px solid #000\">\r\n</canvas>");
        outputFile.println("<script>");
        outputFile.println("class TextBox {");
        outputFile.println("constructor(word,count,x,y,ySpeed,xSpeed,inBox){");
        outputFile.println("this.word = word;");
        outputFile.println("this.count = count;");
        outputFile.println("this.x = x;");
        outputFile.println("this.y = y;");
        outputFile.println("this.ySpeed = ySpeed;");
        outputFile.println("this.xSpeed = xSpeed;");
        outputFile.println("this.inBox = inBox;");
        outputFile.println("}");
        outputFile.println("}");
        outputFile.println("var canvas, ctx;");
        outputFile.println("var WIDTH = 1200, HEIGHT = 600;");
        outputFile.println("var textQueue = [];");
        outputFile.println("var xPos = 100;");
        outputFile.println("var yPos = 100;");
        //print elements
        printTermsToFile(sortedKeys, words, outputFile);
        outputFile.println("function renderText(word,x,y){");
        outputFile.println("ctx.font = \"20px serif\";");
        outputFile.println("ctx.strokeText(word,x,y);");
        outputFile.println("}");
        outputFile.println("function fall(a){");
        outputFile.println("a.y += a.ySpeed;");
        outputFile.println("a.x += a.xSpeed;");
        outputFile.println("if(a.ySpeed > 0 && a.y > HEIGHT - 8 && a.inBox){");
        outputFile.println("a.ySpeed = -0.4;");
        outputFile.println("}");
        outputFile.println("if(a.ySpeed < 0 && a.y <= 22 && a.inBox){");
        outputFile.println("a.ySpeed = 0.4;");
        outputFile.println("}");
        outputFile.println("if(a.xSpeed > 0 && a.x > WIDTH - 135 && a.inBox){");
        outputFile.println("a.xSpeed = -0.4;");
        outputFile.println("}");
        outputFile.println("if(a.xSpeed < 0 && a.x <= 5 && a.inBox){");
        outputFile.println("a.xSpeed = 0.4;");
        outputFile.println("}");
        outputFile.println(
                "if(a.y < HEIGHT - 8 && a.y > 22 && a.x < WIDTH - 135 && a.x > 5){");
        outputFile.println("a.inBox = true;");
        outputFile.println("}");
        outputFile.println("}");
        outputFile.println("function renderTextBox(c){");
        outputFile.println("ctx.font = \"20px serif\";");
        outputFile.println("ctx.strokeText(c.count.toString(), c.x, c.y);");
        outputFile.println("ctx.strokeText(c.word, c.x + 35, c.y);");
        outputFile.println("ctx.fillStyle=\"#000000\";");
        outputFile.println("ctx.strokeRect(c.x - 5, c.y - 22, 140, 30);");
        outputFile.println("ctx.strokeRect(c.x - 5, c.y - 22, 30, 30);");
        outputFile.println("}");
        outputFile.println("function clearscreen(){");
        outputFile.println("ctx.clearRect(0, 0, WIDTH, HEIGHT);");
        outputFile.println("}");
        outputFile.println("function init(){");
        outputFile.println("canvas=document.getElementById(\"canvas\");");
        outputFile.println("ctx=canvas.getContext(\"2d\");");
        outputFile.println("return setInterval(draw,2);");
        outputFile.println("}");
        outputFile.println("function draw(){");
        outputFile.println("clearscreen();");
        outputFile.println("textQueue.forEach(renderTextBox);");
        outputFile.println("textQueue.forEach(fall);");
        outputFile.println("}");
        outputFile.println("init();");
        outputFile.println("</script>");
    }

    /**
     * prints HTML code that isn't dependent on input file to output file.
     *
     * @param sortedKeys
     * @param words
     * @param outputFile
     * @param boringOutput
     * @param inputFile
     */
    public static void generateHTML(Sequence<String> sortedKeys,
            Map<String, Integer> words, SimpleWriter outputFile,
            boolean boringOutput, String inputFile) {
        outputFile.println("<!DOCTYPE html>");
        outputFile.println("<html>");
        outputFile.println("<head>");
        outputFile.println("<meta charset=utf-8>");
        outputFile
                .println("<meta name=viewport content=\"width=device-width\">");
        outputFile.println("<title> Word Counts </title>");
        outputFile.println("</head>");
        outputFile.println("<body>");
        //print header
        outputFile.println("<h2>Words Counted in " + inputFile + "</h2>");
        outputFile.println("<table border=\"1\">");
        outputFile.println("<tbody>");
        if (!boringOutput) {
            outputFile.println("<tr>");
            outputFile.println("<th>Count</th>");
            outputFile.println("<th>Word</th>");
            outputFile.println("</tr>");
        } else {
            outputFile.println("<tr>");
            outputFile.println("<th>Word</th>");
            outputFile.println("<th>Count</th>");
            outputFile.println("</tr>");
        }
        if (boringOutput) {
            printBoringTermsToFile(sortedKeys, words, outputFile);
        }
        outputFile.println("</tbody>");
        outputFile.println("</table>");
        if (!boringOutput) {
            printJS(sortedKeys, words, outputFile);
        }
        outputFile.println("</body>");
        outputFile.println("</html>");
    }

    /**
     *
     * @param sortedKeys
     * @param words
     * @param outputFile
     */
    public static void printTermsToFile(Sequence<String> sortedKeys,
            Map<String, Integer> words, SimpleWriter outputFile) {
        outputFile.println("var strings = [];");
        outputFile.println("var count = [];");
        for (int i = 0; i < sortedKeys.length(); i++) {
            outputFile.print("strings.push(\"" + sortedKeys.entry(i));
            outputFile.println("\");");
            outputFile.print("count.push(" + words.value(sortedKeys.entry(i)));
            outputFile.println(");");
        }
        outputFile.println("for(i = 0; i < " + sortedKeys.length() + "; i++){");
        outputFile.println("var temp = new TextBox();");
        outputFile.println("temp.word = strings[i];");
        outputFile.println("temp.count = count[i];");
        outputFile.println("temp.x = xPos;");
        outputFile.println("temp.y = yPos;");
        outputFile.println("xPos -= 35;");
        outputFile.println("yPos -= 40;");
        outputFile.println("temp.xSpeed = 0.4;");
        outputFile.println("temp.ySpeed = 0.4;");
        outputFile.println("temp.inBox = false;");
        outputFile.println("textQueue.push(temp);");
        outputFile.println("}");
    }

    /**
     *
     * @param sortedKeys
     * @param words
     * @param outputFile
     */
    public static void printBoringTermsToFile(Sequence<String> sortedKeys,
            Map<String, Integer> words, SimpleWriter outputFile) {
        for (int i = 0; i < sortedKeys.length(); i++) {
            outputFile.println("<tr>");
            outputFile.println("<td>" + sortedKeys.entry(i) + "</td>");
            outputFile.println(
                    "<td>" + words.value(sortedKeys.entry(i)) + "</td>");
            outputFile.println("</tr>");
        }
    }

    /**
     * creates a sequence with the keys sorted alphabetically.
     *
     * @param words
     * @return sequence
     */
    public static Sequence<String> sortMapKeys(Map<String, Integer> words) {
        Sequence<String> sortedKeys = new Sequence1L<String>();
        Map<String, Integer> tempWords = new Map1L<String, Integer>();
        while (words.size() > 0) {
            Map.Pair<String, Integer> tempPair = words.removeAny();
            tempWords.add(tempPair.key(), tempPair.value());
            String key = tempPair.key();
            boolean addedKey = false;
            if (sortedKeys.length() == 0) {
                sortedKeys.add(0, key);
                addedKey = true;
            }
            for (int i = 0; !addedKey && i < sortedKeys.length(); i++) {
                if (key.compareTo(sortedKeys.entry(i)) < 0) {
                    sortedKeys.add(i, key);
                    addedKey = true;
                }
            }
            if (!addedKey) {
                sortedKeys.add(sortedKeys.length(), key);
            }

        }
        while (tempWords.size() > 0) {
            Map.Pair<String, Integer> tempPair = tempWords.removeAny();
            words.add(tempPair.key(), tempPair.value());
        }
        return sortedKeys;
    }

    /**
     * returns a set with every letter contained in a word.
     *
     * @return letters
     */
    public static Set<Character> generateLetters() {
        Set<Character> letters = new Set1L<Character>();
        letters.add(',');
        letters.add('.');
        letters.add(' ');
        letters.add('?');
        letters.add('!');
        letters.add(';');
        letters.add(':');
        letters.add('$');
        letters.add('%');
        letters.add('^');
        letters.add('&');
        letters.add('*');
        letters.add('(');
        letters.add(')');
        letters.add('_');
        letters.add('-');
        letters.add('=');
        letters.add('+');
        letters.add('[');
        letters.add(']');
        letters.add('{');
        letters.add('}');
        letters.add('|');
        letters.add('\\');
        letters.add('`');
        letters.add('\"');
        letters.add('>');
        letters.add('<');
        return letters;
    }

    /**
     * reads input file and updates map accordingly.
     *
     * @updates termCounts
     *
     * @param termCounts
     * @param inputFile
     */
    public static void updateMapFromFile(Map<String, Integer> termCounts,
            SimpleReader inputFile) {
        while (!inputFile.atEOS()) {
            int pos = 0;
            String line = inputFile.nextLine();
            String nextLine = line.toLowerCase();
            int endPos = nextLine.length();
            Set<Character> specialChars = new Set1L<Character>();
            specialChars = generateLetters();
            while (pos < endPos) {
                String nextWordOrSeparator = nextWordOrSeparator(nextLine, pos,
                        specialChars);
                pos += nextWordOrSeparator.length();
                if (!specialChars.contains(nextWordOrSeparator.charAt(0))) {
                    if (!termCounts.hasKey(nextWordOrSeparator)) {
                        termCounts.add(nextWordOrSeparator, 1);
                    } else {
                        termCounts.replaceValue(nextWordOrSeparator,
                                termCounts.value(nextWordOrSeparator) + 1);
                    }
                }
            }
        }
    }

    /**
     *
     * @param text
     * @param position
     * @param separators
     * @return nextWordOrSeparator
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        boolean isWord = true;
        String nextWordOrSeparator = "";
        char initialChar = text.charAt(position);
        if (isWord && separators.contains(initialChar)) {
            isWord = false;
        }
        nextWordOrSeparator += text.substring(position, position + 1);
        boolean foundOtherCharType = false;
        if (isWord) {
            while (!foundOtherCharType && position < text.length() - 1) {
                position++;
                if (separators.contains(text.charAt(position))) {
                    foundOtherCharType = true;
                } else {
                    nextWordOrSeparator += text.substring(position,
                            position + 1);
                }
            }
        } else {
            boolean foundSeparator = false;
            while (!foundOtherCharType && position < text.length() - 1) {
                position++;
                if (separators.contains(text.charAt(position))) {
                    nextWordOrSeparator += text.substring(position,
                            position + 1);
                } else {
                    foundOtherCharType = true;
                }
            }
        }
        return nextWordOrSeparator;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        boolean boringOutput;

        out.println("Enter the name of an input file: ");
        String inputFile = in.nextLine();
        out.println("Enter the name of an output file: ");
        String outputFile = in.nextLine();
        out.println("Would you prefer a boring visual?");
        String input = in.nextLine();
        if (input.equals("yes")) {
            boringOutput = true;
        } else {
            boringOutput = false;
        }

        SimpleWriter outFile = new SimpleWriter1L(outputFile);
        SimpleReader inFile = new SimpleReader1L(inputFile);

        Map<String, Integer> terms = new Map1L<String, Integer>();
        Sequence<String> sortedKeys = new Sequence1L<String>();
        updateMapFromFile(terms, inFile);
        sortedKeys = sortMapKeys(terms);
        generateHTML(sortedKeys, terms, outFile, boringOutput, inputFile);

        out.close();
        in.close();
        inFile.close();
    }

}
