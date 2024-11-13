package sk.uniba.fmph.dcs.player_board;

import java.util.Arrays;

public class PlayerTools {
    private final int maxMultipleUseTools = 3; // total number of tool slots
    private final int maxSingleUseTools = 36; // total number of civilisation cards
    private final int maxTools = maxMultipleUseTools + maxSingleUseTools;

    private int[] tools = new int[maxTools];
    // multiple use tools + single use tools,
    // which can be max 36 - number of civilisation cards;

    private boolean[] usedTools = new boolean[maxMultipleUseTools];
    // for one time tools does not need to remember if they were used
    // usedTools[i] is true, if tools[i] was used this turn

    public PlayerTools() {
        Arrays.fill(usedTools, false);
        Arrays.fill(tools, 0);
    }

    public void newTurn() {
        Arrays.fill(usedTools, false);
    }

    public void addTool() {
        int min = 4; // highest value multiple use tool can have is 4
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

    public void addSingleUseTool(int strength) {
        for (int i = maxMultipleUseTools; i < maxTools; i++) {
            if (tools[i] == 0) {
                tools[i] = strength;
                break;
            }
        }
    }

    public Integer useTool(int index) {
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

    // this function returns true, if player has enough value of available tools
    public boolean hasSufficientTools(int goal) {
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
