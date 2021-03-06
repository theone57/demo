package com.all.lin.sign.javadp.template.mcl99;
/*
 * Copyright [2015] [Jeff Lee]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * 模板模式案例 --- 99乘法表打印
 * @author Jeff Lee
 * @since 2015-8-18 17:28:28
 */
public class Test99 {

	public static void main(String[] args) {
		Iterator.iterate(9,new IAction());
	}
}
