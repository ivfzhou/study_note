# 一、笔记

1. https://cn.vuejs.org/guide/introduction.html
1. 模板和大胡子表达式中的 Javascript 表达式解析有限制，不能访问用户添加给 window 的变量。
1. 大胡子语法在标签 textarea 中无效。`<textarea>{{ text }}</textarea>`。
1. 直接在 DOM 中书写模板 (例如原生 template 元素的内容)，模板的编译需要遵从浏览器中 HTML 的解析行为。在这种情况下，应该需要使用 kebab-case 形式并显式地关闭组件的标签。
1. Vue 支持将模板中使用 kebab-case 的标签解析为使用 PascalCase 注册的组件。
1. 组件的 props 名，在定义时采用 camelCase 形式，在标签属性中采用 kebab-case 形式。
1. 子组件不能修改父组件传递的 props，父组件可修改传递的 props，并在子组件中可见。
1. defineProps 等宏，不能访问外部 js 变量。

# 二、例子

1. 选项式与组合式 API：
    ```html
    <script>
      // 选项式
      export default {
          data() {
              return {}
          },
          components: {
              componentName: componentObj
          },
          props: [
              'propName'
          ],
          props: {}
          emits: [
              'eventName'
          ],
          emits: {}
          methods: {
              funcName() {}
          },
          computed：{
              funcName() {}
          }，
          watch: {
              refName: {
                  funcName(new, old) {}
              }
          },
          onBeforeCreated() {},
          onCreated() {},
          onBeforeMount() {},
          onMounted() {},
          onBeforeUpdate() {},
          onUpdated() {},
          onBeforeUnmount() {},
          onUnmounted() {},
          inheritAttrs: false,
          template: ``
      }
      // 组合式
      export default {
          props: [],
          emits: [],
          components: {}, // 注册局部组件，将在当前组件中可使用。在 <script setup> 单文件中，导入的组件可直接使用
          directives: {
              focus: {}
          },
          setup(props, ctx) {
              ctx.emit('enlarge-text')
              ctx.attrs
              provide(key, value)
              const value = inject(key, default, true) // 第三个参数表示默认值应该被当作一个工厂函数
              provide('read-only-count', readonly(count))
              return {}
          },
          template: `` // 实例追踪处理的模板，如果过不提供，则会对挂载的元素的 innerHTML 作为模板。可以给 css id 选择器作为值
      }
    </script>
    ```
1. 全局构建版本，没有构建步骤：
    ```html
    <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
    <div id="app">{{ message }}</div>
    <script>
        const { createApp, ref } = Vue;
        createApp({
            setup() {
                const message = ref("Hello vue!");
                return {
                    message,
                };
            },
        }).mount("#app");
    </script>
    ```
1. ES 模块版本，没有构建步骤：
    ```html
    <script type="importmap">
        {
            "imports": {
                "vue": "https://unpkg.com/vue@3/dist/vue.esm-browser.js"
            }
        }
    </script>
    <div id="app">{{ message }}</div>
    <script type="module">
        import { createApp, ref } from "vue";
        // import { createApp, ref } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'
        createApp({
            setup() {
                const message = ref("Hello Vue!");
                return {
                  message,
                };
            },
        }).mount("#app");
    </script>
    ```
1. 模板语法：
    ```vue
    <!-- 文本插值 -->
    <span>{{ msg }}</span>
    
    <!-- 将内容以 HTML 格式填充到 span 标签中 -->
    <span v-html="rawHtml"></span>
    
    <!-- 属性绑定 -->
    <div v-bind:id="dynamicId"></div>
    
    <!-- js 变量值是 null/undefined，则移除属性 -->
    <div :id="dynamicId"></div>
    
    <!-- 绑定属性 id，值为 js 变量 id 的值 -->
    <div :id></div>
    <div v-bind:id></div>
    
    <!-- js 变量是真值或者空串时，渲染属性 -->
    <button :disabled="isButtonDisabled"></button>
    
    <!-- js 对象变量展开，键是属性名，值是属性值 -->
    <div v-bind="objectOfAttrs"></div>
    
    <!-- 绑定事件 -->
    <a v-on:click="doSomething"></a>
    <a @click="doSomething"></a>
    
    <!-- 传递原生事件对象 -->
    <button @click="doSomething($event)"></button>
    <button @click="(event) => doSomething(event)"></button>
    
    <!-- 动态参数，绑定的属性名是 js 变量 attributeName 的值。如果值是 null，则移除属性 -->
    <a v-bind:[attributeName]="url"></a>
    <a :[attributeName]="url"></a>
    <a v-on:[eventName]="doSomething"></a>
    <a @[eventName]="doSomething"></a>
    
    <!-- 双向数据绑定 -->
    <input v-model="text" />
    
    <!-- 事件修饰符 -->
    <form @submit.prevent="doSomething"></form>
    <!-- 可省略值 -->
    <form @submit.prevent></form>
    <!-- 修饰语可以使用链式书写，相关代码是以相同的顺序生成 -->
    <a @click.stop.prevent="doSomething"></a>
    
    <!-- 指令 -->
    <!-- 在同一元素中 v-if 比 v-for 优先处理，因此 v-if 不能使用 v-for 中产生的变量 -->
    <p v-if="seen"></p>
    <p v-else-if="seen"></p>
    <p v-else></p>
    
    <!-- template 中使用比较特殊，会移除 template 元素包裹 -->
    <template v-if></template>
    
    <!-- 切换 css display 属性 -->
    <h1 v-show="seen"></h1>
    
    <!-- 提供 key 值方便 vue 识别管理 -->
    <li v-for="(value, index) in array" :key="index"></li>
    
    <!-- template 中使用比较特殊，会移除 template 元素包裹 -->
    <template v-for></template>
    
    <!-- 可使用解构语法 -->
    <li v-for="({ message }, index) in items"></li>
    
    <!-- 使用 of 代替 in -->
    <li v-for="(value, index) of array"></li>
    
    <!-- 可遍历对象 -->
    <li v-for="(value, key, index) in object"></li>
    
    <!-- n = [1, 10] -->
    <span v-for="n in 10"></span>
    ```
1. 模板引用，获取 DOM 对象：
    ```vue
    <script setup>
        import { ref, onMounted } from "vue";
    
        // 声明一个 ref 来存放该元素的引用
        const input = ref(null);
    
        onMounted(() => {
            input.value.focus(); // 在组件挂载后才能访问模板引用，否则为 null
        });
    </script>
    
    <template>
        <input ref="input" />
    </template>
    ```
    ```vue
    <!-- 获取 DOM 对象数组，ref 数组并不保证与源数组相同的顺序 -->
    <script setup>
        import { ref, onMounted } from "vue";
    
        const itemRefs = ref([]);
    
        onMounted(() => console.log(itemRefs.value));
    </script>
    
    <template>
        <ul>
            <li v-for="item in list" ref="itemRefs">
                {{ item }}
            </li>
        </ul>
    </template>
    ```
    ```vue
    <input :ref="(el) => { /* 每次组件更新时都被调用，当绑定的元素被卸载时，函数也会被调用一次，此时的 el 参数会是 null。 */ }">
    ```
    ```vue
    <!-- 组件上的 ref。使用了 <script setup> 的组件是默认私有，子组件通过 defineExpose 宏显式暴露 -->
    <!-- 父组件 -->
    <script setup>
        import { ref, onMounted } from "vue";
        import Child from "./Child.vue";
        const child = ref(null);
        onMounted(() => {
            // child.value 是 <Child /> 组件的实例
        });
    </script>
    <template>
        <child ref="child"></child>
    </template>
    
    <!-- 子组件 -->
    <script setup>
        import { ref } from "vue";
        const a = 1;
        const b = ref(2);
        // 像 defineExpose 这样的编译器宏不需要导入
        defineExpose({
            a,
            b,
        });
    </script>
    ```
1. class 属性的绑定：
    ```vue
    <!-- js 变量 isActive 值的真假决定是否应用 active 类 -->
    <div :class="{ active: isActive }"></div>
    
    <!-- 与 class 共存，最后合并结果 -->
    <div class="static" :class="{ active: isActive, 'text-danger': hasError }"></div>
    
    <!-- 类为字符串数组元素的值 -->
    <div :class="[activeClass, errorClass]"></div>
    <div :class="[isActive ? activeClass : '', errorClass]"></div>
    <div :class="[{ activeClass: isActive }, errorClass]"></div>
    
    <!-- 在组件上使用，将同组件根元素 class 值合并 -->
    <MyComponent class="baz boo"></MyComponent>
    ```
1. style 属性绑定：
    ```vue
    <!-- 同 class 绑定行为一致，可以绑定数组和对象 -->
    <!-- 样式提供多套值，只应用对浏览器支持的值 -->
    <div :style="{ display: ['-webkit-box', '-ms-flexbox', 'flex'] }"></div>
    ```
1. 事件修饰符：
   1. prevent：阻止事件的默认行为。
   1. self：只能是元素自身触发事件。
   1. capture：以捕获模式处理事件。先处理外层元素。
   1. once：事件只触发一次。
   1. passive：滚动事件的默认行为 (scrolling) 将立即发生而非等待 onScroll 完成。
   1. enter：按键是 Enter 时触发事件。可以使用任何 KeyboardEvent.key 暴露的按键名，书写风格为 kebab-case。鼠标按键 left、right、middle。
   1. exact：精确按下某些按键，且未按其他按键，才触发事件。
   1. stop：停止冒泡，调用 event.stopPropagation()。
1. 特殊元素值绑定：
    ```vue
    <!-- toggle 的值为 dynamicTrueValue 或 dynamicFalseValue -->
    <input type="radio" v-model="toggle" :true-value="dynamicTrueValue" :false-value="dynamicFalseValue" />
    ```
1. v-model 上的修饰符：
    ```vue
    <!-- change 事件后更新 msg -->
    <input v-model.lazy="msg" />
    
    <!-- 输入的数据将被 parseFloat() 处理。type="number" 时自动启用 -->
    <input v-model.number="age" />
    
    <!-- 自动去除输入数据的两端空格 -->
    <input v-model.trim="msg" />
    ```
1. 计算属性默认只读。使用提供 setter 的方式创建计算属性，可以修改计算属性值：
    ```vue
    <script setup>
        import { ref, computed } from "vue";
        const firstName = ref("John");
        const lastName = ref("Doe");
        const fullName = computed({
            get() {
                return firstName.value + " " + lastName.value;
            },
            set(newValue) {
                [firstName.value, lastName.value] = newValue.split(" ");
            },
        });
    </script>
    ```
1. watch 的用法：
    ```vue
    <script>
        const x = ref(0);
        const y = ref(0);
    
        // 单个 ref
        watch(x, (newX) => {});
    
        // getter 函数
        watch(
            () => x.value + y.value,
            (sum) => {}
        );
    
        // 多个来源组成的数组
        watch([x, () => y.value], ([newX, newY]) => {
            console.log(`x is ${newX} and y is ${newY}`);
        });
    
        // getter 用法只会在返回值变了时才触发监听，加上 deep 参数转成深层监听。
        watch(
            () => state.someObject,
            (newValue, oldValue) => {
                // 注意：newValue 此处和 oldValue 是相等的
                // 除非 state.someObject 被整个替换了
            },
            { deep: true }
        );
    
        // 立即执行一次监听
        watch(
            source,
            (newValue, oldValue) => {
                // 立即执行，且当 source 改变时再次执行
            },
            { immediate: true }
        );
    
        // 一次性监听
        watch(
            source,
            (newValue, oldValue) => {
                // 当 source 变化时，仅触发一次
            },
            { once: true }
        );
    
        // 侦听器回调会在父组件更新 (如有) 之后、所属组件的 DOM 更新之前被调用。
        // 使用该参数，在侦听器回调中能访问被 Vue 更新之后的所属组件的 DOM。
        watch(source, callback, {
            flush: "post",
        });
    
        // 同步侦听器，在 Vue 进行任何更新之前触发
        watch(source, callback, {
            flush: "sync",
        });
        </script>
    ```
1. watchEffect 用法：
    ```vue
    <script setup>
        // 立即执行一次监听，然后在响应式依赖（todoId.value）变化时触发监听。
        // 仅会在其同步执行期间，才追踪依赖。在使用异步回调时，只有在第一个 await 正常工作前访问到的属性才会被追踪。
        watchEffect(async () => {
            const response = await fetch(
                `https://jsonplaceholder.typicode.com/todos/${todoId.value}`
            );
            data.value = await response.json();
        });
    
        // 侦听器回调会在父组件更新 (如有) 之后、所属组件的 DOM 更新之前被调用。
        // 使用该参数，在侦听器回调中能访问被 Vue 更新之后的所属组件的 DOM
        watchEffect(callback, {
            flush: "post",
        });
        watchPostEffect(() => {
            /* 在 Vue 更新后执行 */
        });
    
        // 同步侦听器，在 Vue 进行任何更新之前触发
        watchEffect(callback, {
            flush: "sync",
        });
        watchSyncEffect(() => {
            /* 在响应式数据变化时同步执行 */
        });
    
        // 停止监听
        const unwatch = watchEffect(() => {});
        unwatch();
    </script>
    ```
1. 父组件触发子组件事件：
    ```vue
    <!-- 子组件 -->
    <script setup>
        const emit = defineEmits(["enlarge-text"]);
    </script>
    <template>
        <button @click="$emit('enlarge-text')">Enlarge text</button>
    </template>
    
    <!-- 父组件 -->
    <blog-post @enlarge-text="postFontSize += 0.1"></blog-post>
    ```
1. 组件间的 v-model，子组件改变值，父组件中跟随改变：
    ```vue
    <!-- 父组件 -->
    <script setup>
        import { ref } from "vue";
        const msg = ref("");
    </script>
    <template>
        <child v-model="msg"></child>
    </template>
    
    <!-- 子组件 -->
    <script setup>
        const msg = defineModel({ required: true });
    </script>
    <template>{{ msg }}<button @click="msg = 'child'"></button></template>
    ```
    ```vue
    <!-- 可以附加属性使用 -->
    <!-- 子组件 -->
    <script setup>
        const title = defineModel("title"， {});
    </script>
     
    <!-- 父组件 -->
    <template>
        <child v-model:title="xxx"></child>
    </template>
    ```
    ```vue
    <!-- v-model 的修饰符 -->
    <!-- 子组件 -->
    <script setup>
        const [msg, modifiers] = defineModel({
            set(value) {
                if (modifiers.capitalize) {
                    return value.charAt(0).toUpperCase() + value.slice(1);
                }
                return value;
            },
        });
    </script>
    
    <!-- 父组件 -->
    <my-component v-model:xxx.capitalize="myText"></my-component>
    ```
1. 组件 props 声明写法：
    ```vue
    <script setup>
        const props = defineProps(["foo"]);
        defineProps({
            title: String,
            likes: Number,
            propB: [String, Number, null], // Boolean 类型默认值是 false。不传递或传递 undefined 会使用默认值。
            propC: {
                type: String,
                required: true,
            },
            propE: {
                type: Number,
                default: 100,
            },
            propF: {
                type: Object,
                // 该函数接收组件所接收到的原始 prop 作为参数。
                default(rawProps) {
                  return { message: "hello" };
                },
            },
            propG: {
                validator(value, props) {
                  return ["success", "warning", "danger"].includes(value);
                },
            },
            propH: {
                type: Function,
                default() {
                  return "Default function";
                },
            },
        });
    </script>
    ```
1. 组件 emits 声明写法：
    ```vue
    <!-- 子组件 -->
    <script setup>
        const emit = defineEmits(["inFocus", "submit"]);
        function buttonClick() {
            emit("submit");
        }
    
        // 事件校验
        const emit = defineEmits({
            // 没有校验
            click: null,
            // 校验 submit 事件
            submit: ({ email, password }) => {},
        });
    </script>
    
    <!-- 父组件 -->
    <template>
        <my-button @increase-by="(n) => (count += n)"></my-button>
        <my-button @increase-by="increaseCount"></my-button>
    </template>
    ```
1. 禁止组件属性透传，子组件没声明 props 的属性传递就是透传，透传的 attr 将合并应用到单根元素上：
    ```vue
    <script setup>
        defineOptions({
            inheritAttrs: false,
        });
    
        // 访问所有透传的 attr
        import { useAttrs } from "vue";
        const attrs = useAttrs();
    </script>
    
    <!-- $attr 能访问所有透传的 attr -->
    <button class="btn" v-bind="$attrs">Click Me</button>
    ```
1. 组件插槽：
    ```vue
    <!-- 子组件 -->
    <template>
        <!-- 被父组件给的内容替换 -->
        <slot>如果父组件没提供，slot 标签内的数据将是默认的内容</slot>
    </template>
    
    <!-- 父组件 -->
    <child>
        Somthing
    </child>
    ```
    ```vue
    <!-- 具名插槽 -->
    <!-- 子组件 -->
    <template>
        <solt name="header"></solt>
        <solt></solt>
    </template>
    
    <!-- 父组件 -->
    <child>
        Somthing <!-- 隐式默认插槽内容 -->
        <template v-slot:header>Somthing</template>
        <template v-slot:[slotName]>Somthing</template> <!-- 动态插槽名 -->
        <template #default>Somthing</template> <!-- 显示提供默认插槽内容 -->
    </child>
    ```
    ```vue
    <!-- 条件插槽 -->
    <!-- 子组件 -->
    <template>
        <!-- 当父组件提供了 header 插槽时才渲染 -->
        <div v-if="$slot.header"></div>
        <template></template>
    </template>
    ```
    ```vue
    <!-- 子插槽向父组件传递信息 -->
    <!-- 子组件 -->
    <template>
        <slot name="header" age="18"></slot>
    </template>
    
    <!-- 父组件 -->
    <template>
        <child>
            <template v-slot:header="{ age }"></template>
        </child>
        <child v-slot="obj"> {{ obj.age }} </child>
    </template>
    ```
1. 动态组件，组件切换：
    ```vue
    <!-- is 的值是组件名或组件对象 -->
    <component :is="tabs[currentTab]"></component>
    ```
1. DOM 中编写组件的写法：
    ```html
    <!-- tr 标签替换成 blog-post-row 组件 -->
    <table>
        <tr is="vue:blog-post-row"></tr>
    </table>
    ```
1. 注册全局组件：
     ```vue
     <script setup>
        import { createApp } from "vue";
        import MyComponent from "./App.vue";
     
        const app = createApp({});
        // 全局组件可以在应用中的组件模板中使用
        app.component("MyComponent", MyComponent);
        app.component(
            // 注册的名字
            "MyComponent",
            // 组件的实现
            {
              /* ... */
            }
        );
     </script>
     ```
1. 异步组件：
    ```vue
    <script setup>
    import { defineAsyncComponent } from "vue";
    
    const AdminPage = defineAsyncComponent(() =>
        import("./components/AdminPageComponent.vue")
    );
    </script>
    
    <template>
        <admin-page></admin-page>
    </template>
    ```
    ```vue
    <script>
    // 注册全局异步组件
    app.component(
        "MyComponent",
        defineAsyncComponent(() => import("./components/MyComponent.vue"))
    );
    
    const AsyncComp = defineAsyncComponent({
        // 加载函数
        loader: () => import("./Foo.vue"),
    
        // 加载异步组件时使用的组件
        loadingComponent: LoadingComponent,
        // 展示加载组件前的延迟时间，默认为 200ms
        delay: 200,
    
        // 加载失败后展示的组件
        errorComponent: ErrorComponent,
        // 如果提供了一个 timeout 时间限制，并超时了
        // 也会显示这里配置的报错组件，默认值是：Infinity
        timeout: 3000,
    });
    </script>
    ```
1. 自定义指令：
    ```vue
    <script setup>
    // 在模板中启用 v-focus
    const vFocus = {
        mounted: (el) => el.focus(),
    };
    </script>
    
    <template>
        <input v-focus />
    </template>
    ```
1. 插件的使用：
    ```javascript
    import { createApp } from "vue";
    
    const app = createApp({});
    
    app.use((app, options) => {}, {
      /* 可选的选项 */
    });
    ```

# 三、Vue Router

1. 在组件中使用：
    ```vue
    <script setup>
    import { useRoute, useRouter } from "vue-router";
    const router = useRouter();
    const route = useRoute();
    </script>
    ```
1. 参数路由：
    ```vue
    <script>
    const routes = [
        // 动态字段以冒号开始
        { path: "/users/:id", component: User },
    ];
    </script>
    
    <!-- 组件中使用 -->
    <script setup>
    const route = useRoute();
    route.params.id;
    </script>
    ```
