/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.datavines.server.api.dto.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.datavines.common.enums.JobType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class JobVO implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private String name;

    private String schemaName;

    private String tableName;

    private String columnName;

    private JobType type;

    private String updater;

    private List<SlaVO> slaList;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * cron 表达式
     */
    private String cronExpression;

    /**
     * 总执行次数
     */
    private Integer totalCount;

    /**
     * 执行成功次数
     */
    private Integer successCount;

    /**
     * 执行失败次数
     */
    private Integer failCount;

    /**
     * 首次执行时间
     */
    private String firstJobExecutionTime;

    /**
     * 最近一次执行时间
     */
    private String lastJobExecutionTime;

    public String getType() {
        return type.getDescription();
    }
}
