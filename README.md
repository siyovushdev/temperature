# temperature

```kotlin
val array = arrayOf(
    "su", "-c", "busybox stty 4800 -F /dev/ttyS1"
)
val array2 = arrayOf(
    "su", "-c", "busybox head -n 1 /dev/ttyS1"
)

var process: Process = Runtime.getRuntime().exec(array)
process = Runtime.getRuntime().exec(array2)
val result = process.inputStream.bufferedReader().readLine().trim()
```
