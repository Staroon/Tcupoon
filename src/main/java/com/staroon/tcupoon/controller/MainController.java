package com.staroon.tcupoon.controller;

import com.staroon.tcupoon.common.Config;
import com.staroon.tcupoon.common.ConfigTool;
import com.staroon.tcupoon.common.UploadTool;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018/5/17
 * Time: 8:58
 */
public class MainController implements Initializable {

    @FXML
    private Label setCos;

    @FXML
    private Label help;

    @FXML
    private Label about;

    @FXML
    private Pane getFile;

    @FXML
    private Label text;

    @FXML
    private Button copyUrl;

    @FXML
    private Hyperlink clickOutUrl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clickOutUrl.setVisible(false);
//        clickOutUrl.setDisable(true);
    }

    ConfigTool configTool = new ConfigTool();
    UploadTool fileUpload = new UploadTool();


    public void toConfig(MouseEvent event) throws Exception {
        Parent config = FXMLLoader.load(getClass().getResource("/Config.fxml"));
        Scene configScene = new Scene(config);
        Stage configStage = new Stage();
        configStage.setTitle("设置");
        configStage.setScene(configScene);
        configStage.setResizable(false);
        configStage.show();
    }

    public void toHelp(MouseEvent event) throws Exception {
        Parent help = FXMLLoader.load(getClass().getResource("/Help.fxml"));
        Scene helpScene = new Scene(help);
        Stage helpStage = new Stage();
        helpStage.setTitle("帮助");
        helpStage.setScene(helpScene);
        helpStage.setResizable(false);
        helpStage.show();
    }

    public void toAbout(MouseEvent event) throws Exception {
        Parent about = FXMLLoader.load(getClass().getResource("/About.fxml"));
        Scene aboutScene = new Scene(about);
        Stage aboutStage = new Stage();
        aboutStage.setTitle("关于");
        aboutStage.setScene(aboutScene);
        aboutStage.setResizable(false);
        aboutStage.show();
    }

    public void getFilePath(DragEvent event) throws Exception {
        Dragboard dragboard = event.getDragboard();
        String filePath = null;
        if (dragboard.hasFiles()) {
            File file = dragboard.getFiles().get(0);
            if (file != null) {
                filePath = file.getPath();
            }
        }

        Config tcupConfig = configTool.getConfig();
        String outUrl = fileUpload.uploadFile(tcupConfig, filePath);

        if (outUrl == null || "".equals(outUrl)) {
            text.setText("       文件上传失败");
            return;
        }

        text.setText("       文件上传成功");
        String finalOutUrl = outUrl;
        clickOutUrl.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    java.awt.Desktop.getDesktop().browse(new URI(finalOutUrl));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
        clickOutUrl.setVisible(true);

        if (tcupConfig.getNoCopy() == 1) {
            return;
        }

        if (tcupConfig.getCopyMdUrl() == 1) {
            outUrl = "![img](" + outUrl + ")";
        }

        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(outUrl);
        clipboard.setContent(clipboardContent);
    }

    public void getFileOver(DragEvent event) {
        if (event.getGestureSource() != getFile) {
            event.acceptTransferModes(TransferMode.ANY);
            text.setText("将文件拖入此区域上传");
            clickOutUrl.setVisible(false);
        }
    }

    public void setAboutInColor(MouseEvent event) {
        about.setTextFill(Color.web("#4169E1"));
    }

    public void setConfigInColor(MouseEvent event) {
        setCos.setTextFill(Color.web("#4169E1"));
    }

    public void setHelpInColor(MouseEvent event) {
        help.setTextFill(Color.web("#4169E1"));
    }

    public void setAboutOutColor(MouseEvent event) {
        about.setTextFill(Color.web("#000000"));
    }

    public void setConfigOutColor(MouseEvent event) {
        setCos.setTextFill(Color.web("#000000"));
    }

    public void setHelpOutColor(MouseEvent event) {
        help.setTextFill(Color.web("#000000"));
    }

}
