package com.joao.view;

import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import java.util.ArrayList;

public class MainFrameWorker extends MainFrame {
    public MainFrameWorker() {
        super(new String[] {"create", "read"});
    }

    @Override
    protected void displayFrames(Object item) {
        methods = new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(new CreateFrameCar());
                add(new ReadFrameCar());
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameMotorcycle());
                add(new ReadFrameMotorcycle());
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameBus());
                add(new ReadFrameBus());
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameUserWorker());
                add(new ReadFrameUser());
            }});
        }};

        manageFrames(item);
    }
}
