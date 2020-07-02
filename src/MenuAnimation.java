import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu, Animation {

    private boolean stop = false;
    private List<MenuHelper> menuList;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner runner;
    private List<MenuHelper> subMenus;

    /**
     * Instantiates a new Menu animation.
     *
     * @param keyboardSensor keyboard sensor
     * @param runner         the runner
     */
    public MenuAnimation(KeyboardSensor keyboardSensor, AnimationRunner runner) {
        this.menuList = new ArrayList<>();
        this.keyboardSensor = keyboardSensor;
        this.runner = runner;
        this.subMenus = new ArrayList<>();
    }

    /**
     * Add selection.
     *
     * @param key       the key
     * @param message   the message
     * @param returnVal the return val
     */
    @Override
    public void addSelection(String key, String message, Object returnVal) {
        this.menuList.add(new MenuHelper<>(key, message, returnVal));
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    @Override
    public T getStatus() {
        for (int i = 0; i < this.menuList.size(); i++) {
            if (this.keyboardSensor.isPressed(this.menuList.get(i).getKey())) {
                this.stop = !this.stop;
                return (T) this.menuList.get(i).getReturnVal();
            }
        }
        for (int i = 0; i < this.subMenus.size(); i++) {
            if (this.keyboardSensor.isPressed(this.subMenus.get(i).getKey())) {
                runner.run((Animation) this.subMenus.get(i).getReturnVal());
                Menu<T> menu = (Menu<T>) this.subMenus.get(i).getReturnVal();
                Task<T> task = (Task<T>) menu.getStatus();
                task.run();
                return null;
            }
        }
        return null;

    }

    /**
     * Name: addSubMenu.
     *
     * @param key     the key.
     * @param message the message.
     * @param subMenu the subMenu.
     */
    @Override
    public void addSubMenu(String key, String message, Menu subMenu) {
        this.subMenus.add(new MenuHelper(key, message, subMenu));
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(0x1B5476));
        d.drawText(301, 101, "Arkanoid", 35);
        d.drawText(201, 141, "Please choose an option:", 30);
        d.setColor(new Color(0x428FC0));
        d.drawText(300, 100, "Arkanoid", 35);
        d.drawText(200, 140, "Please choose an option:", 30);
        d.setColor(new Color(0x1B6211));
        int i = 0;
        for (MenuHelper m : menuList) {
            d.drawText(202, 212 + (80 * i), "(" + m.getKey() + ") " + m.getMessage(), 32);
            i++;
        }

        for (MenuHelper m : subMenus) {
            d.drawText(202, 252, "(" + m.getKey() + ") " + m.getMessage(), 32);
            i++;
        }

        d.setColor(new Color(0x35C846));
        i = 0;
        for (MenuHelper m : menuList) {
            d.drawText(202, 210 + (80 * i), "(" + m.getKey() + ") " + m.getMessage(), 32);
            i++;
        }
        for (MenuHelper m : subMenus) {
            d.drawText(202, 252, "(" + m.getKey() + ") " + m.getMessage(), 32);
            i++;
        }

        this.getStatus();
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Sets stop.
     *
     * @param stopp the stop
     */
    public void setStop(boolean stopp) {
        this.stop = stopp;
    }

}
