# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.

Feature: Step - cap()

  Scenario: g_V_group_byXnameX
    Given the modern graph
    And the traversal of
      """
      g.V().group().by("name")
      """
    When iterated to list
    Then the result should be unordered
      | result |
      | m[{"ripple":"l[v[ripple]]", "vadas":"l[v[vadas]]", "lop":"l[v[lop]]", "peter": "l[v[peter]]", "josh": "l[v[josh]]", "marko":"l[v[marko]]"}] |
    
  Scenario: g_V_chooseXlabel_person__age_groupCountXaX__name_groupCountXbXX_capXa_bX
    Given an unsupported test
    Then nothing should happen because
      """
      The result returned is not supported under GraphSON 2.x and therefore cannot be properly asserted. More
      specifically it has numeric keys which basically get toString()'d under GraphSON 2.x. This test can be supported
      with GraphSON 3.x.
      """