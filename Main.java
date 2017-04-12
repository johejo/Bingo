import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        int N;
        String str = readAll("boards.txt");
        String[] str2 = str.split("\n");
        N = str2.length;
        String[][] str4 = new String[N][N];
        int[][] boards = new int[N][N];

        for (int i = 0; i < str2.length; i++){
            str4[i] = str2[i].split(" ");
        }
        for (int i = 0; i < str4.length; i++){
            for (int j = 0; j < str4.length; j++){
                boards[i][j] = Integer.parseInt(str4[i][j]);
            }
        }

        String str3 = readAll("selected.txt");
        String[] str5 = str3.split("\n");
        int[] selected = new int[str5.length];

        for (int i = 0; i < str5.length; i++){
            selected[i] = Integer.parseInt(str5[i]);
        }

        int bingoNum = 0, reachNum = 0;
        int[][] row = new int[N][N];
        int[][] col = new int[N][N];
        int[][] naname = new int[2][N];
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards.length; j++) {
                col[i][j] = boards[j][i];
                row[i][j] = boards[i][j];

                if (i == j) {
                    naname[0][i] = boards[i][j];
                }
                if (i + j == N - 1) {
                    naname[1][i] = boards[i][j];
                }
            }
        }

        for (int[] i : col) {
            if (check(i, selected).equals("Bingo")){
                bingoNum++;
            } else if (check(i, selected).equals("Reach")){
                reachNum++;
            }
        }

        for (int[] i : row) {
            if (check(i, selected).equals("Bingo")){
                bingoNum++;
            } else if (check(i, selected).equals("Reach")){
                reachNum++;
            }
        }

        for (int[] i : naname) {
            if (check(i, selected).equals("Bingo")){
                bingoNum++;
            } else if (check(i, selected).equals("Reach")){
                reachNum++;
            }
        }

        System.out.println("BINGO:"+bingoNum+"\nREACH:"+reachNum);

    }

    public static String readAll(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }

    public static String check(int[] boards, int[] selected){
        int count = 0;

        for (int i = 0; i < boards.length; i++){
            for (int j = 0; j < selected.length; j++){
                if(boards[i] == selected[j]){
                    count++;
                }
            }
        }
        if (count == boards.length) {
            return "Bingo";
        } else if (count == boards.length - 1) {
            return "Reach";
        } else {
            return "NOT";
        }
    }
}
