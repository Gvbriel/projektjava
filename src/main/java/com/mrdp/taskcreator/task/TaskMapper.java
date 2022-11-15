package com.mrdp.taskcreator.task;

import com.mrdp.taskcreator.task.dao.Task;
import com.mrdp.taskcreator.task.dto.TaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto mapDto(Task task);
}
