package sk.uniba.fmph.dcs.player_board;

import java.util.Arrays;

public class PlayerTools {
    private final int maxMultipleUseTools = 3; // total number of tool slots
    private final int maxSingleUseTools = 3; // total number of civilisation cards
    private final int maxTools = maxMultipleUseTools + maxSingleUseTools;
    private final int[] tools = new int[maxTools];
    private final int maxStrength = 4;
    // multiple use tools + single use tools,
    // which can be max 36 - number of civilisation cards;
    private final boolean[] usedTools = new boolean[maxMultipleUseTools];
    // for one time tools does not need to remember if they were used
    // usedTools[i] is true, if tools[i] was used this turn

    /**
     * Initialise.
     */
    public PlayerTools() {
        Arrays.fill(usedTools, false);
        Arrays.fill(tools, 0);
    }

    /**
     * Calculates and returns the total count of tools available to the player.
     *
     * @return the total number of tools
     */
    public int getToolsCount() {
        int toolsCount = 0;
        for (int i = 0; i < this.maxMultipleUseTools; i++){
            toolsCount += tools[i];
        }

        return toolsCount;
    }

    /**
     * Resets tools such that all are unused.
     */
    public void newTurn() {
        Arrays.fill(usedTools, false);
    }

    /**
     * Upgrades first tool, which can be upgraded.
     */
    public void addTool() {
        int min = maxStrength; // highest value multiple use tool can have is 4
        int minIndex = -1;
        for (int i = 0; i < maxMultipleUseTools; i++) {
            if (tools[i] < min) {
                min = tools[i];
                minIndex = i;
            }
        }
        // we find first tool, which is not upgraded, and we upgrade it
        // this will find first tool, which is bellow level of other tools
        // and it will upgrade it

        if (minIndex == -1) {
            return;
        }
        tools[minIndex]++;
    }

    /**
     * Adds single use tool of value strength.
     *
     * @param strength
     *            - value of Single use tool.
     */
    public void addSingleUseTool(final int strength) {
        if (strength <= 0 || this.maxStrength < strength) {
            return;
        }

        for (int i = maxMultipleUseTools; i < maxTools; i++) {
            if (tools[i] == 0) {
                tools[i] = strength;
                break;
            }
        }
    }

    /**
     * @param index
     *            - tools, which is going to be used.
     *
     * @return value of tools[index] or null, if tools[index] cant be used.
     */
    public Integer useTool(final int index) {
        if (index < 0 || index > maxTools) {
            return null;
        }
        if (index < maxMultipleUseTools) {
            if (usedTools[index]) {
                return null;
            }
            usedTools[index] = true;
            return tools[index];
        }
        if (tools[index] == 0) {
            return null;
        }

        int ans = tools[index];
        tools[index] = 0;
        return ans;
    }

    /**
     * @param goal
     *            - number we need to achieve.
     *
     * @return - returns true if sum of available tools is at least goal.
     */
    public boolean hasSufficientTools(final int goal) {
        int totalToolValue = 0;
        for (int i = 0; i < maxMultipleUseTools; i++) {
            if (!usedTools[i]) {
                totalToolValue += tools[i];
            }
        }
        for (int i = maxMultipleUseTools; i < maxTools; i++) {
            totalToolValue += tools[i];
        }
        return totalToolValue >= goal;
    }

    /**
     * @return state of PlayerTools. Only includes Single use tools, if they are available.
     */
    public String state() {
        StringBuilder ans = new StringBuilder();
        ans.append("multiple use tools:");
        for (int i = 0; i < maxMultipleUseTools; i++) {
            ans.append(" tools[").append(i).append("] = ").append(tools[i]).append(", used = ").append(usedTools[i]);
            if (i != maxMultipleUseTools - 1) {
                ans.append(",");
            }
        }
        ans.append("\nsingle use tools:");
        for (int i = maxMultipleUseTools; i < maxTools; i++) {
            if (tools[i] != 0) {
                ans.append(" tools[").append(i).append("] = ").append(tools[i]);
                if (i != maxTools - 1) {
                    ans.append(",");
                }
            }
        }
        return ans.toString();
    }
}
