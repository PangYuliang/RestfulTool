/*
  Copyright (C), 2018-2020, ZhangYuanSheng
  FileName: ToolwindowListenerImpl
  Author:   ZhangYuanSheng
  Date:     2020/6/12 17:12
  Description: 
  History:
  <author>          <time>          <version>          <desc>
  作者姓名            修改时间           版本号              描述
 */
package core.service.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;
import com.intellij.util.messages.MessageBus;
import core.service.topic.RefreshServiceTreeTopic;
import org.jetbrains.annotations.NotNull;

/**
 * @author ZhangYuanSheng
 * @version 1.0
 */
public class ToolWindowListenerImpl implements ToolWindowManagerListener {

    private final Project project;

    public ToolWindowListenerImpl(Project project) {
        this.project = project;
    }

    @SuppressWarnings("MissingRecentApi")
    @Override
    public void toolWindowShown(@NotNull String id, @NotNull ToolWindow toolWindow) {
        String restfulTool = "RestfulTool";
        if (restfulTool.equals(id)) {
            MessageBus bus = project.getMessageBus();
            RefreshServiceTreeTopic publisher = bus.syncPublisher(RefreshServiceTreeTopic.TOPIC);
            publisher.refresh();
        }
    }
}
