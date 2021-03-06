package com.staroon.tcupoon.controller;

import com.staroon.tcupoon.model.Config;
import com.staroon.tcupoon.tools.ConfigTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018/5/17
 * Time: 15:33
 */
public class ConfigController implements Initializable {
    @FXML
    private TextField secretId;

    @FXML
    private PasswordField secretKey;

    @FXML
    private TextField appId;

    @FXML
    private TextField bucketName;

    @FXML
    private TextField region;

    @FXML
    private TextField cosPath;

    @FXML
    private Hyperlink bucketPage;

    @FXML
    private Hyperlink regionPage;

    @FXML
    private Hyperlink keyPage;

    @FXML
    private RadioButton copyUrl;

    @FXML
    private RadioButton copyMdUrl;

    @FXML
    private RadioButton noCopy;

    @FXML
    private Label pathSign;

    @FXML
    private Button save;

    private final ToggleGroup group = new ToggleGroup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pathSign.setText("");
        save.setTextFill(Color.BLACK);
        save.setText("    保存    ");
        Config defaultConfig = ConfigTool.getConfig();

        secretId.setText(defaultConfig.getSecretId());
        secretKey.setText(defaultConfig.getSecretKey());
        appId.setText(defaultConfig.getAppId());
        bucketName.setText(defaultConfig.getBucketName());
        region.setText(defaultConfig.getRegion());
        cosPath.setText(defaultConfig.getCosPath());

        copyUrl.setToggleGroup(group);
        copyUrl.setSelected(defaultConfig.getCopyUrl() == 1);
        copyMdUrl.setToggleGroup(group);
        copyMdUrl.setSelected(defaultConfig.getCopyMdUrl() == 1);
        noCopy.setToggleGroup(group);
        noCopy.setSelected(defaultConfig.getCopyUrl() == 0 && defaultConfig.getCopyMdUrl() == 0);
    }

    public void toBucketPage(ActionEvent action) throws Exception {
        java.awt.Desktop.getDesktop().browse(new URI("https://console.cloud.tencent.com/cos/bucket"));

    }

    public void toRegionPage(ActionEvent action) throws Exception {
        java.awt.Desktop.getDesktop().browse(new URI("https://cloud.tencent.com/document/product/436/6224"));
    }

    public void toKeyPage(ActionEvent action) throws Exception {
        java.awt.Desktop.getDesktop().browse(new URI("https://console.cloud.tencent.com/cam/capi"));
    }

    public void clickCosPath(MouseEvent event) throws Exception {
        pathSign.setText("");
        save.setTextFill(Color.BLACK);
        save.setText("    保存    ");
    }

    public void clickRegion(MouseEvent event) throws Exception {
        pathSign.setText("");
        save.setTextFill(Color.BLACK);
        save.setText("    保存    ");
    }

    public void clickBucket(MouseEvent event) throws Exception {
        pathSign.setText("");
        save.setTextFill(Color.BLACK);
        save.setText("    保存    ");
    }

    public void clickAppId(MouseEvent event) throws Exception {
        pathSign.setText("");
        save.setTextFill(Color.BLACK);
        save.setText("    保存    ");
    }

    public void clickSKey(MouseEvent event) throws Exception {
        pathSign.setText("");
        save.setTextFill(Color.BLACK);
        save.setText("    保存    ");
    }

    public void clickSId(MouseEvent event) throws Exception {
        pathSign.setText("");
        save.setTextFill(Color.BLACK);
        save.setText("    保存    ");
    }

    public void clickAutoCopy(MouseEvent event) throws Exception {
        pathSign.setText("");
        save.setTextFill(Color.BLACK);
        save.setText("    保存    ");
    }
    public void clickAutoCopyMd(MouseEvent event) throws Exception {
        pathSign.setText("");
        save.setTextFill(Color.BLACK);
        save.setText("    保存    ");
    }
    public void clickNoCopy(MouseEvent event) throws Exception {
        pathSign.setText("");
        save.setTextFill(Color.BLACK);
        save.setText("    保存    ");
    }

    public void saveConfig(ActionEvent action) throws Exception {

        if (cosPath.getText().startsWith("/") || cosPath.getText().endsWith("/")) {
            pathSign.setText("   首尾不能添加\"/\"");
            save.setTextFill(Color.RED);
            save.setText(" 保存失败 ");
            return;
        }

        Config currentConfig = new Config();

        currentConfig.setSecretId(secretId.getText().trim());
        currentConfig.setSecretKey(secretKey.getText().trim());
        currentConfig.setAppId(appId.getText().trim());
        currentConfig.setBucketName(bucketName.getText().trim());
        currentConfig.setRegion(region.getText().trim());
        currentConfig.setCosPath(cosPath.getText().trim());

        if (group.getSelectedToggle() != null) {
            String select = group.getSelectedToggle().toString().split("=")[1].split(",")[0].trim();
//            System.out.println(select);
            switch (select) {
                case "noCopy":
                    currentConfig.setCopyUrl(0);
                    currentConfig.setCopyMdUrl(0);
                    currentConfig.setNoCopy(1);
                    break;
                case "copyUrl":
                    currentConfig.setCopyUrl(1);
                    currentConfig.setCopyMdUrl(0);
                    currentConfig.setNoCopy(0);
                    break;
                default:
                    currentConfig.setCopyUrl(0);
                    currentConfig.setCopyMdUrl(1);
                    currentConfig.setNoCopy(0);
                    break;
            }
        }

        ConfigTool.writeConfig(currentConfig);
        save.setTextFill(Color.BLACK);
        save.setText(" 保存成功 ");
    }
}
