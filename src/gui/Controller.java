package gui;

import equation.AdditionOperation;
import equation.BinaryOperation;
import equation.Exercise;
import equation.SubstractOperation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Administrator
 */
public class Controller implements Initializable {

    public Controller() {
        totalNumber = 16;
    }

    @FXML
    private Pagination pageBar;
    @FXML
    private Menu qusOperationMenu;
    @FXML
    private HBox qusOperation;
    @FXML
    private HBox qusZone;
    @FXML
    private MenuItem menuImport;
    @FXML
    private MenuItem menuExport;
    @FXML
    private RadioMenuItem menuGenAdd;
    @FXML
    private ToggleGroup typeGen;
    @FXML
    private RadioMenuItem menuGenSub;
    @FXML
    private RadioMenuItem menuGenMix;
    @FXML
    private RadioMenuItem menuAmt20;
    @FXML
    private ToggleGroup amountGen;
    @FXML
    private RadioMenuItem menuAmt40;
    @FXML
    private RadioMenuItem menuAmt60;
    @FXML
    private RadioMenuItem menuAmt80;
    @FXML
    private RadioMenuItem menuAmt100;
    @FXML
    private MenuItem menuReGen;
    @FXML
    private MenuItem menuClear;
    @FXML
    private MenuItem menuSubmit;
    @FXML
    private Button btnImport;
    @FXML
    private Button btnExport;
    @FXML
    private Button btnReGen;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnLastPage;
    @FXML
    private Button btnNextPage;
    @FXML
    private Label qus1;
    @FXML
    private TextField ans1;
    @FXML
    private Label qus5;
    @FXML
    private TextField ans5;
    @FXML
    private Label qus9;
    @FXML
    private TextField ans9;
    @FXML
    private Label qus13;
    @FXML
    private TextField ans13;
    @FXML
    private Label qus2;
    @FXML
    private TextField ans2;
    @FXML
    private Label qus6;
    @FXML
    private TextField ans6;
    @FXML
    private Label qus10;
    @FXML
    private TextField ans10;
    @FXML
    private Label qus14;
    @FXML
    private TextField ans14;
    @FXML
    private Label qus3;
    @FXML
    private TextField ans3;
    @FXML
    private Label qus7;
    @FXML
    private TextField ans7;
    @FXML
    private Label qus11;
    @FXML
    private TextField ans11;
    @FXML
    private Label qus15;
    @FXML
    private TextField ans15;
    @FXML
    private Label qus4;
    @FXML
    private TextField ans4;
    @FXML
    private Label qus8;
    @FXML
    private TextField ans8;
    @FXML
    private Label qus12;
    @FXML
    private TextField ans12;
    @FXML
    private Label qus16;
    @FXML
    private TextField ans16;
    @FXML
    private Text currentPage;
    @FXML
    private Text totalPage;
    @FXML
    private Label status;
    @FXML
    private Label result;

    /**
     * amount of equation
     * @value 20, 40, 60, 80, 100
     */
    private int totalNumber;

    /**
     * flag for what kinds of equation
     * @value add, sub, mix
     */
    private String qusType = "";
    String userAns[] = null;
    HashMap<String, TextField> ansMap = new HashMap<String, TextField>();
    HashMap<String, Label> qusMap = new HashMap<String, Label>();
    Exercise exercise = new Exercise();

    @FXML
    void clearAns(ActionEvent event) {
        for (int i = 1; i <= 16; i++) {
            ansMap.get("ans" + i).setText("");
            ansMap.get("ans" + i).setStyle("-fx-background-color: white");
//            ans[i].setText("");
        }
        status.setText("");
        result.setText("");
    }

    void clearQus() {
        for (int i = 1; i <= 16; i++) {
            qusMap.get("qus" + i).setText("");
        }
    }

    @FXML
    void genAddQus(ActionEvent event) {
        reableItems();
        clearAns(event);
        qusType = "add";
        exercise.generateAdditionExercise(totalNumber);
        displayQus(exercise);
    }

    @FXML
    void genMixQus(ActionEvent event) {
        reableItems();
        clearAns(event);
        qusType = "mix";
        exercise.generateMixExercise(totalNumber);
        displayQus(exercise);
    }

    @FXML
    void genSubQus(ActionEvent event) {
        reableItems();
        clearAns(event);
        qusType = "sub";
        exercise.generateSubstractionExercise(totalNumber);
        displayQus(exercise);
    }

    @FXML
    void genQus100(ActionEvent event) {
        generateQus(100);
    }

    @FXML
    void genQus20(ActionEvent event) {
        generateQus(20);
    }

    @FXML
    void genQus40(ActionEvent event) {
        generateQus(40);
    }

    @FXML
    void genQus60(ActionEvent event) {
        generateQus(60);
    }

    @FXML
    void genQus80(ActionEvent event) {
        generateQus(80);
    }

    @FXML
    void qusExport(ActionEvent event) {
        try {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(filter);
            fc.setMultiSelectionEnabled(false);
            int result = fc.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                if (!file.getPath().endsWith(".txt")) {
                    file = new File(file.getPath() + ".txt");
                }
                Writer out = null;
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    out = new FileWriter(file);
                    out.write("[" + exercise.size() + "#" + exercise.getExerciseType() + "] ");
                    for (int i = 0; i < exercise.size(); i++) {
                        BinaryOperation anOperation = exercise.getOperation(i);
                        if (anOperation != null) {
                            out.write(anOperation.asString() + " ");
                        }
                    }
                    out.flush();
                    out.close();
                    // Alert Message Box to prompt
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("提示");
                    alert.setHeaderText(null);
                    alert.setContentText("成功导出至目录!");
                    alert.showAndWait();
                } catch (IOException e) {
                    // Alert Message Box to prompt
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText(null);
                    alert.setContentText("导出失败!");
                    alert.showAndWait();
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Alert Message Box
        }
    }

    @FXML
    void qusImport(ActionEvent event) {
        try {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(filter);
            fc.setMultiSelectionEnabled(false);
            fc.setDialogTitle("请选择要导入的文件");
            int result = fc.showDialog(null, "导入");
            if (JFileChooser.APPROVE_OPTION == result) {
                File file = fc.getSelectedFile();
                Reader reader = new FileReader(file);
                BufferedReader br = new BufferedReader(reader);
                StringBuilder builder = new StringBuilder();
                builder = builder.append(br.readLine());
                String s = builder.toString();
                int amount = Integer.parseInt(s.split("#")[0].substring(1));
                String[] questionSet = new String[amount + 1];
                questionSet = s.split(" ");
                BinaryOperation op;
                exercise.clear();
                for (int i = 1; i <= amount; i++) {
                    if (questionSet[i].indexOf("+") != -1) {
                        op = new AdditionOperation();
                        op.contruct(questionSet[i]);
                    } else {
                        op = new SubstractOperation();
                        op.contruct(questionSet[i]);
                    }
                    exercise.add(op);
//                    exercise.setCursor(0);
                }
                reader.close();

                // update view
                displayQus(exercise);
                reableItems();

                // Alert Message Box to prompt
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText(null);
                alert.setContentText("成功导入题目!");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Alert Message Box
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText(null);
            alert.setContentText("导入失败!");
            alert.showAndWait();
        }
    }

    @FXML
    void menuAbout(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("关于");
        alert.setHeaderText("口算练习 V1.0");
        alert.setContentText("by gongcy\ngongcy126@126.com\n2016-12-15");
        alert.showAndWait();
    }

    @FXML
    void reGenQus(ActionEvent event) {
        if (qusType.equals("add")) {
            genAddQus(event);
        } else if (qusType.equals("sub")) {
            genSubQus(event);
        } else if (qusType.equals("mix")) {
            genMixQus(event);
        } else {
            genMixQus(event);
        }
    }

    @FXML
    void submitAns(ActionEvent event) {
        int correctNum = 0;
        int i = 1;
        exercise.moveToHead();
        while (exercise.hasNext()) {
            TextField temp = ansMap.get("ans" + i);
            if (String.valueOf(exercise.next().getResult()).equals(temp.getText())) {
                correctNum++;
                temp.setStyle("-fx-background-color: lightgreen");
            } else {
                temp.setStyle("-fx-background-color: pink");
            }
            i++;
        }

        // calculate correct rate
        DecimalFormat df = new DecimalFormat("#.00");

        String qusTypeString = "";
        if (qusType.equals("add")) {
            qusTypeString = "加法题目";
        } else if (qusType.equals("sub")) {
            qusTypeString = "减法题目";
        } else if (qusType.equals("mix")) {
            qusTypeString = "加减混合";
        }

        status.setText("状态：题目类型-【" + qusTypeString + "】 题目数量-【"
                + totalNumber + "】");
        result.setText("答题统计：正确题数【" + correctNum + "】  错误题数【"
                + (totalNumber - correctNum) + "】 正确率【"
                + df.format(correctNum * 100 / totalNumber)
                + "%】 错误率【"
                + df.format((totalNumber - correctNum) * 100 / totalNumber)
                + "%】");
    }

    public void reableItems() {
        qusOperation.setDisable(false);
        qusZone.setDisable(false);
        qusOperationMenu.setDisable(false);
    }

    public void generateQus(int totalNumber) {
        userAns = new String[totalNumber + 1];
        this.totalNumber = totalNumber;
        qusOperation.setDisable(false);
        qusZone.setDisable(false);
        qusOperationMenu.setDisable(false);
        exercise.generateMixExercise(totalNumber);
        currentPageNum = 1;
        displayQus(exercise);
    }

    private int currentPageNum = 1;
    private final int pageSize = 16;
    private int totalPageNum;

    @FXML
    void lastPage(ActionEvent event) {
        updatePage();
//        recordUserAns();
//        clearQus();
        boolean canGoBack = currentPageNum != 1;
        boolean canGoFwd = currentPageNum != totalPageNum;
        btnNextPage.setDisable(!canGoFwd);
        btnLastPage.setDisable(!canGoBack);
        if (--currentPageNum <= 0) {
            currentPageNum = 1;
        }
        exercise.setCursor(currentPageNum * pageSize);
        displayQus(exercise);

    }

    @FXML
    void nextPage(ActionEvent event) {
        updatePage();
        if (++currentPageNum > totalPageNum) {
            currentPageNum = totalPageNum;
        }
//        recordUserAns();
//        clearQus();
        boolean canGoBack = currentPageNum != 1;
        boolean canGoFwd = currentPageNum != totalPageNum;
        btnNextPage.setDisable(!canGoFwd);
        btnLastPage.setDisable(!canGoBack);
        exercise.setCursor(currentPageNum * pageSize);
        displayQus(exercise);
    }

    public void update() {
        int start = (currentPageNum - 1) * pageSize;
        int end = start + pageSize;
        if (end >= exercise.size()) {
            end = exercise.size();
        }
    }

    public void updatePage() {
        currentPage.setText(String.valueOf(currentPageNum));
        totalPageNum = (totalNumber / pageSize == 0) ? (totalNumber / pageSize) : (totalNumber / pageSize + 1);
        totalPage.setText(String.valueOf(totalPageNum));
    }

    public void displayQus(Exercise ex) {
        updatePage();
        int i = 1;
        while (ex.hasNext()) {
            if (i > pageSize) {
                btnNextPage.setDisable(false);
            }
            if (i % pageSize == 0) {
                i = pageSize;
            } else {
                i = i % pageSize;
            }
            qusMap.get("qus" + i).setText(ex.next().asString());
            i++;
        }
        pageBar.setPageCount(totalPageNum);
//        pageBar.setPageFactory(new Callback<Integer, Node>() {
//            @Override
//            public Node call(Integer param) {
////                nextPage(event);
//                System.out.println(param);
//                return qusZone;
//            }
//        });
//        pageBar.set
    }

    public void recordUserAns() {
        int j = currentPageNum * pageSize + 1;
        for (int i = 1; i <= pageSize && j <= totalNumber; i++) {
            userAns[j] = ansMap.get("ans" + i).getText().trim();
        }
    }

    public void paginate() {

    }

    /*
    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        int page = pageIndex * itemsPerPage();
        for (int i = page; i < page + itemsPerPage(); i++) {
            VBox element = new VBox();
            Hyperlink link = new Hyperlink("Item " + (i + 1));
            link.setVisited(true);
            Label text = new Label("Search results\nfor " + link.getText());
            element.getChildren().addAll(link, text);
            box.getChildren().add(element);
        }
        return box;
    }
     */
    public void paginate_new() {
        pageBar = new Pagination(10, 1);
//        pageBar.
//        totalNumber % 16;
        pageBar.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                qusZone = new HBox();
                qusZone.getChildren().addAll();
                return qusZone;
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        paginate_new();
        qusZone.setDisable(true);
        if (qusType == null) {
            qusOperation.setDisable(true);
            qusOperationMenu.setDisable(true);
        }

        TextField ans0 = null;
        TextField ans[] = new TextField[]{
                ans0, ans1, ans2, ans3, ans4, ans5, ans6, ans7, ans8,
                ans9, ans10, ans11, ans12, ans13, ans14, ans15, ans16
        };
        Label qus0 = null;
        Label qus[] = new Label[]{
                qus0, qus1, qus2, qus3, qus4, qus5, qus6, qus7, qus8,
                qus9, qus10, qus11, qus12, qus13, qus14, qus15, qus16
        };
        for (int i = 1; i <= 16; i++) {
            ansMap.put("ans" + i, ans[i]);
            qusMap.put("qus" + i, qus[i]);
        }
    }

}
