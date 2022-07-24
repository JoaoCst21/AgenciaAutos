package com.joao.view;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import java.util.ArrayList;

public class MainFrameAdmin extends MainFrame{
    public MainFrameAdmin() {
        super(new String[] {"create", "read", "update", "delete"});
    }

    @Override
    protected void displayFrames(Object item) {
        methods = new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(new CreateFrameCar());
                add(new ReadFrameCar());
                add(new UpdateFrameCar());
                add(new DeleteFrameCar());
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameMotorcycle());
                add(new ReadFrameMotorcycle());
                add(new UpdateFrameMoto());
                add(new DeleteFrameMoto());
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameBus());
                add(new ReadFrameBus());
                add(new UpdateFrameBus());
                add(new DeleteFrameBus());
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameUserAdmin());
                add(new ReadFrameUser());
                add(new UpdateFrameUser());
                add(new DeleteFrameUser());
            }});
        }};

        manageFrames(item);
    }
}
