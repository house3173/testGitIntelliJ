package com.example.guithongbao;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GuiThongBao implements Initializable {

    @FXML
    private RadioButton chonDanCu;

    @FXML
    private RadioButton chonHodan;

    @FXML
    private RadioButton chonToanBo;

    @FXML
    private TextArea dsNguoiNhan;

    @FXML
    private ListView<String> listCanSelect;

    @FXML
    private AnchorPane listSelect;

    @FXML
    private ToggleGroup selectGroup;

    @FXML
    private AnchorPane thongBao;

    @FXML
    private TextArea noidung;

    @FXML
    private TextField tieude;

    @FXML
    private AnchorPane textReceiver;

    @FXML
    private Label labelListReceiver;

    @FXML
    private AnchorPane tieuDeSection;

    String[] userList = {"Trịnh Văn Hậu - 038203015401",
                     "Tạ Quang Phổ - 038203015402",
                     "Nguyễn Văn Duy - 038203015403",
                    "Trịnh Văn Hà - 038203015404",
                    "Tạ Quang Bửu - 038203015405",
                    "Nguyễn Văn Dương - 038203015406",
                    "Trịnh Văn Hải - 038203015407",
                    "Tạ Quang - 038203015408",
                    "Nguyễn Văn Hùng - 038203015409"};

    String[] homeList = {
            "1 - Trịnh Văn Hậu",
            "2 - Tạ Quang Phổ",
            "3 - Nguyễn Văn Duy",
            "4 - Phạm Việt Hùng",
            "5 - Phạm Văn Hải"
    };
    String currentList = "";

    ObservableList<String> emptyList = FXCollections.observableArrayList();

    String emptyString = "";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listSelect.setVisible(false);
        textReceiver.setVisible(true);

        dsNguoiNhan.setOnMouseClicked(event -> {
            if(selectGroup.getSelectedToggle() != null) {
                RadioButton sl = (RadioButton) selectGroup.getSelectedToggle();

                if (sl.equals(chonDanCu) || sl.equals(chonHodan)) {
                    listSelect.setVisible(true);
                }
            }
        });

        tieude.setOnMouseClicked(event -> {
            listSelect.setVisible(false);
        });

        noidung.setOnMouseClicked(event -> {
            listSelect.setVisible(false);
        });

        dsNguoiNhan.textProperty().addListener(event -> {
            currentList = dsNguoiNhan.getText();
        });

        // Đặt ToggleGroup cho các RadioButton
        chonDanCu.setToggleGroup(selectGroup);
        chonHodan.setToggleGroup(selectGroup);
        chonToanBo.setToggleGroup(selectGroup);

        selectGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {

            if (selectGroup.getSelectedToggle() != null) {
                RadioButton sl = (RadioButton) selectGroup.getSelectedToggle();

                if (sl.equals(chonToanBo)) {
                    listSelect.setVisible(false);
                    dsNguoiNhan.setText("Thông báo này sẽ được gửi tới toàn bộ dân cư!");
                    labelListReceiver.setText("Danh sách người nhận:");
                    dsNguoiNhan.setEditable(false);
                } else if (sl.equals(chonDanCu)) {
                    textReceiver.setVisible(true);
                    labelListReceiver.setText("Danh sách người nhận:");
                    dsNguoiNhan.setEditable(true);
                    dsNguoiNhan.setText(emptyString);
                    emptyList.clear();
                    listCanSelect.setItems(emptyList);
                    listCanSelect.getItems().addAll(userList);
                } else {
                    textReceiver.setVisible(true);
                    labelListReceiver.setText("Danh sách hộ nhận:");
                    dsNguoiNhan.setEditable(true);
                    dsNguoiNhan.setText(emptyString);
                    emptyList.clear();
                    listCanSelect.setItems(emptyList);
                    listCanSelect.getItems().addAll(homeList);
                }

                listCanSelect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                        String selected = listCanSelect.getSelectionModel().getSelectedItem();
                        if (!currentList.contains(selected)) {
                            currentList = currentList + selected + "\n";
                            dsNguoiNhan.setText(currentList);
                        }
                    }
                });
            }
        });

    }

}
