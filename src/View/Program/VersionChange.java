/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Administrator
 */
public class VersionChange {

    private final List<JFormattedTextField> versionCompoment;
    private final List<String> versionElements;
    private final boolean isInput;

    public VersionChange(JFormattedTextField txtF_major,
            JFormattedTextField txtF_minor,
            JFormattedTextField txtF_patcher,
            JFormattedTextField txtF_revision, boolean isInput) {
        this.isInput = isInput;
        this.versionCompoment = new ArrayList<>();
        this.versionElements = new ArrayList<>();
        this.versionCompoment.add(txtF_major);
        this.versionCompoment.add(txtF_minor);
        this.versionCompoment.add(txtF_patcher);
        this.versionCompoment.add(txtF_revision);
        if (isInput) {
            this.versionElements.addAll(Arrays.asList("1.0.0.0".split("\\.")));
        }
        for (JFormattedTextField jFormattedTextField : versionCompoment) {
            jFormattedTextField.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (isInput) {
                        return;
                    }
                    if (evt.getSource() instanceof JFormattedTextField jText && contain(jText)) {
                        eventTxtFormatClick(jText);
                    }
                }
            });
        }

    }

    
    public String getVersion() {
        StringBuilder version = new StringBuilder();
        for (JFormattedTextField textField : versionCompoment) {
            version.append(textField.getText());
            if (versionCompoment.indexOf(textField) < versionCompoment.size() - 1) {
                version.append(".");
            }
        }
        return version.toString();
    }

    public final void setVersion(String version) throws Exception {
        if (isInput) {
            return;
        }
        if (version == null || !version.matches("^[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]$")) {
            throw new Exception(String.format("Version format illegal: %s (%%d.%%d.%%d.%%d)", version));
        } else {
            this.versionElements.addAll(Arrays.asList(version.split("\\.")));
            resetVersion();
        }
    }
    
    private void eventTxtFormatClick(JFormattedTextField eventformatTxt) {
        if (this.versionElements.isEmpty()) {
            return;
        }
        if (getValueFormat(eventformatTxt) == null) {
            setDefaultValue(eventformatTxt);
        } else if (hasAdd()) {
            resetVersion();
        } else {
            upVersion(eventformatTxt);
        }
    }

    private void setDefaultValue(JFormattedTextField eventformatTxt) {
        eventformatTxt.setValue(0);
    }

    private Integer getValueFormat(JFormattedTextField eventformatTxt) {
        String value = eventformatTxt.getText();
        if (value == null || !value.matches("[0-9]+")) {
            return null;
        } else {
            return Integer.valueOf(value);
        }
    }

    private void upVersion(JFormattedTextField eventformatTxt) {
        eventformatTxt.setValue(getValueFormat(eventformatTxt) + 1);
        for (int i = this.versionCompoment.indexOf(eventformatTxt) + 1; i < this.versionCompoment.size();) {
            setDefaultValue(this.versionCompoment.get(i++));
        }
    }

    private boolean hasAdd() {
        for (JFormattedTextField textField : versionCompoment) {
            String oldValue = this.versionElements.get(this.versionCompoment.indexOf(textField));
            String newValue = textField.getText();
            if (!oldValue.equalsIgnoreCase(newValue)) {
                return true;
            }
        }
        return false;
    }

    private void resetVersion() {
        int index = 0;
        for (JFormattedTextField textField : versionCompoment) {
            textField.setText(this.versionElements.get(index++));
        }
    }

    private boolean contain(JFormattedTextField jText) {
        return this.versionCompoment.contains(jText);
    }
}
