
import DreamUI.components.*;
import DreamUI.utils.ImageUtils;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Window extends DreamFrame {

    private DreamPanel body, content;
    Image icon = ImageUtils.resize((BufferedImage) ImageUtils.getImageFromUrl("https://i.imgur.com/Ir30QMW.png"),20,20);
    public Window() throws IOException {
        super("DreamUI",ImageUtils.resize((BufferedImage) ImageUtils.getImageFromUrl("https://i.imgur.com/Ir30QMW.png"),20,20));
        DreamTabbedPane tPane = new DreamTabbedPane();
        tPane.addTab("Tab 1", body = new DreamPanel());
        tPane.addTab("Tab 2", new DreamPanel());
        tPane.addTab("Tab 3", new DreamPanel());
        tPane.addTab("Tab 4", new DreamPanel());
        setSize(500,600);
        setLocationRelativeTo(null);
        add(tPane, BorderLayout.CENTER);
        body.setBorder(new EmptyBorder(7,8,7,8));


        body.add(content = new DreamPanel(), BorderLayout.NORTH);
        GridLayout grid = new GridLayout(0,2);
        grid.setVgap(5);

        content.setLayout(grid);


        content.add(new DreamLabel("Text field:"));
        content.add(new DreamTextField());
        content.add(new DreamLabel("Hint text field:"));
        content.add(new DreamHintTextField("Input hint"));
        content.add(new DreamLabel("Icon Hint text field:"));
        content.add(new DreamHintTextField("Input hint",icon));
        content.add(new DreamLabel("Checkbox:"));
        content.add(new DreamCheckBox("Enable rendering"));
        content.add(new DreamLabel("Button:"));
        content.add(new DreamButton("Button"));
        content.add(new DreamLabel("Icon Button:"));
        content.add(new DreamButton("Icon Button" ,icon ));
        content.add(new DreamLabel("Combobox:"));
        content.add(new DreamComboBox<>("Sample #1","Sample #2", "Sample #3", "Sample #4"));
        content.add(new DreamLabel("Icon Combobox:"));

        Map<Object, Icon> materialIcons = new HashMap<Object, Icon>();
        materialIcons.put("Reeeeeee" ,new ImageIcon(icon));
        materialIcons.put("Reeeeeeee" ,new ImageIcon(icon));
        materialIcons.put("Reeeeeeeee" ,new ImageIcon(icon));
        content.add(new DreamComboBox<>(materialIcons));
        content.add(new DreamLabel("Slider:"));
        content.add(new DreamSlider(250,0,500));
        content.add(new DreamLabel("Spinner:"));
        content.add(new DreamSpinner(0,0,100,1));
        DreamList<DreamLabel> l = new DreamList<>();
        for (int i=0; i<20; i++){
            l.add(new DreamLabel("Label #" + (i+1)));
        }
        DreamScrollPane dsp = new DreamScrollPane(l);
        body.add(dsp, BorderLayout.SOUTH);
    }

    public static void main(String... args) throws IOException {
        Window frame = new Window();
        frame.setVisible(true);
    }
}
