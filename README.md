# Lab 11 — Log4j ашиглан Debug хийх

**Хичээл:** F.CSM202 Объект хандлагат программчлал  
**Багш:** А.Отгонбаяр

---

## Ангиудын тайлбар

### BankAccount
Банкны данс удирдах анги. `deposit()`, `withdraw()`, `getBalance()` методуудтай.  
Log4j-ийн 6 бүх түвшнийг (TRACE, DEBUG, INFO, WARN, ERROR, FATAL) зориулалтын дагуу хэрэглэсэн.

### Customer
Хэрэглэгчийн анги. `getDomain()` метод нь email-ийн домэйн нэрийг буцаана.  
Анх `email=null` үед NullPointerException гарч байсан — логоор олж засав.

---

## log4j2.xml хэрхэн тохируулсан

- **Console appender:** `%d %t %-5level %c{1}:%L - %msg%n` форматаар терминалд харуулна
- **RollingFile appender:** `logs/app.log` файлд хадгална
- **Даалгавар 1.1 (ROLL_BY_SIZE):** `SizeBasedTriggeringPolicy size="1MB"` — 1MB болоход шинэ файл үүсгэнэ
- **Root level:** `debug` — TRACE-аас бусад бүх лог бичигдэнэ

---

## Алхам 2.1 — 6 түвшний лог гарч байгаа эсэх

`Main.java`-с дараах 7 туршилт хийж, бүх түвшний лог гарсныг шалгасан:

| Туршилт | Дуудсан үйлдэл | Гарах лог түвшин |
|---|---|---|
| 1 | `deposit(500)` | TRACE, DEBUG, INFO |
| 2 | `withdraw(200)` | TRACE, DEBUG, INFO |
| 3 | `deposit(-100)` | TRACE, WARN |
| 4 | `withdraw(999999)` | TRACE, DEBUG, ERROR |
| 5 | `getBalance()` | TRACE |
| 6 | `simulateFatalError()` | FATAL |
| 7 | `logSensitiveAction(...)` | INFO (маскалсан) |

---

## Алхам 4.1 — tail -f яагаад cat/less-ээс дээр вэ?

`tail -f` нь файлд шинээр бичигдэх лог мөр бүрийг **шууд харуулдаг** тул программ ажиллаж байх үед алдааг цаг алдалгүй тогтоох боломжтой. Харин `cat` нь файлын тухайн үеийн агуулгыг нэг удаа харуулаад дуусдаг, `less` нь ч мөн адил гараар дахин нээх хэрэгтэй болдог. Debug хийхэд хамгийн чухал нь **шууд харах** чадвар тул `tail -f` хамгийн тохиромжтой.

---

## Алхам 3 — Алдааг логоор хэрхэн олж засав

**Алдаа:** `Customer.getDomain()` дотор `email=null` үед `NullPointerException` гарч байв.

**Олсон арга:**
1. `logger.trace("getDomain() дуудагдлаа. email={}", email)` лог нэмсэн
2. Консолд `email=null` гэж харагдав → алдааны эх үүсвэр тодорхой болсон

**Засвар:**
```java
if (email == null) {
    logger.warn("email утга null байна. 'UNKNOWN' буцаана.");
    return "UNKNOWN";
}
```

---

## Ажиллуулах заавар

```bash
# 1. Compile
mvn package -q

# 2. Ажиллуулах
mvn exec:java -Dexec.mainClass="com.lab11.Main"

# 3. Log файл шинжлэх
grep "ERROR" logs/app.log
grep "WARN"  logs/app.log
awk '{print $3}' logs/app.log | sort | uniq -c
```
