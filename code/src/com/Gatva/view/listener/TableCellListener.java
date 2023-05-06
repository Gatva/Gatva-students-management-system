package com.Gatva.view.listener;
/**
 * 自定义单元格数据监听器
 * @since 11:03
 * @author wuguidong@cskaoyan.onaliyun.com
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*
 * 自定义单元格数据监听器通过 TableCellEditor 侦听对表中数据所做的更改。
 *      编辑开始时，单元格的值被保存，当编辑停止时，新值被保存。
 *      这样就获取了 old 和 new 两个不同的值。
 *      仅当 old 和 new 值不同时，则调用提供的 Action 完全想要执行的逻辑。
 *      其中 Action 是一个 TableCellListener 的实例对象。
 *
 */
public class TableCellListener implements PropertyChangeListener, Runnable {
    private JTable table;
    private Action action;
    private int row;
    private int column;
    private Object oldValue;
    private Object newValue;

    /**
     * 创建一个 TableCellListener。
     *
     * @param table  要监视数据更改的表
     * @param action 数据变更后执行的操作/事件
     */
    public TableCellListener(JTable table, Action action) {
        this.table = table;
        this.action = action;
        this.table.addPropertyChangeListener(this);
    }

    /**
     * 创建一个 TableCellListener
     * 其中包含与给定单元格的数据更改相关的所有数据的副本。
     *
     * @param row 更改单元格的行
     * @param column 更改单元格的列
     * @param oldValue 更改单元格的旧数据
     * @param newValue 更改单元格的新数据
     */
    private TableCellListener(JTable table, int row, int column, Object oldValue, Object newValue) {
        this.table = table;
        this.row = row;
        this.column = column;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    /**
     * 获取上次编辑的列
     * @return 被编辑的列
     */
    public int getColumn() {
        return column;
    }

    /**
     * @return 单元格的新值
     */
    public Object getNewValue() {
        return newValue;
    }

    /**
     * @return 单元格的旧值
     */
    public Object getOldValue() {
        return oldValue;
    }

    /**
     * 获取上次编辑的行
     *
     * @return 上次编辑的行
     */
    public int getRow() {
        return row;
    }

    /**
     * 获取被改变的单元格的表格
     *
     * @return 被更改的单元格的表格
     */
    public JTable getTable() {
        return table;
    }

    // 实现 PropertyChangeListener 接口
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        // A cell has started/stopped editing
        if ("tableCellEditor".equals(e.getPropertyName())) {
            if (table.isEditing()) {
                //System.out.printf("tableCellEditor is editing..%n");
                processEditingStarted();
                return;
            }
            processEditingStopped();
        }
    }

    /*
     * 保存要编辑的单元格信息
     */
    private void processEditingStarted() {
        /*
        invokeLater 是必需的，因为在触发“tableCellEditor”PropertyChangeEvent 时尚未设置表的编辑行和编辑列。这会导致调用“run”方法
         */
        SwingUtilities.invokeLater(this);
    }

    /*
     * 如上
     */
    @Override
    public void run() {
        row = table.convertRowIndexToModel(table.getEditingRow());
        column = table.convertColumnIndexToModel(table.getEditingColumn());
        oldValue = table.getModel().getValueAt(row, column);
        //这里应对oldValue为null的情况做处理，否则将导致原值与新值均为空时仍被视为值改变
        if (oldValue == null) {
            oldValue = "";
        }
        newValue = null;
    }

    /*
     *  必要时更新单元格历史记录
     */
    private void processEditingStopped() {
        newValue = table.getModel().getValueAt(row, column);
        //这里应对newValue为null的情况做处理，否则后面会抛出异常
        if (newValue == null) {
            newValue = "";
        }
        // 数据已更改，调用提供的 Action
        if (!newValue.equals(oldValue)) {
            // 复制数据以防另一个单元格开始编辑
            // 在处理此更改时
            TableCellListener tcl = new TableCellListener(
                    getTable(), getRow(), getColumn(), getOldValue(), getNewValue());
            ActionEvent event = new ActionEvent(
                    tcl,
                    ActionEvent.ACTION_PERFORMED,
                    "");
            action.actionPerformed(event);
        }
    }
}

