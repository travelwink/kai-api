/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

export default {
  'locales': {
    'availableLocales': ['zh-CN', 'de', 'en'],
    'fallbackLocale': 'zh-CN'
  },
  shortcuts: {
    claimTask: {
    key: 'ctrl+alt+c',
//    description: 'claims the currently selected task'
    description: '认领当前选中的任务'
    },
    focusFilter: {
    key: 'ctrl+shift+f',
//    description: 'set the focus to the first filter in the list'
    description: '将焦点设置到列表中的第一个筛选条件'
    },
    focusList: {
    key: 'ctrl+alt+l',
//    description: 'sets the focus to the first task in the list'
    description: '将焦点设置到列表中的第一个任务'
    },
    focusForm: {
    key: 'ctrl+alt+f',
//    description: 'sets the focus to the first input field in a task form'
    description: '将焦点设置到任务表单中的第一个输入框'
    },
    startProcess: {
    key: 'ctrl+alt+p',
//    description: 'opens the start process modal dialog'
    description: '打开启动流程的模态对话框'
    }
  }
};
