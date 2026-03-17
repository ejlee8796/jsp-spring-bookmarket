1:-- MySQL dump 10.15  Distrib 10.0.21-MariaDB, for Linux (x86_64)
2:--
3:-- Host: localhost    Database: adnerwin
4:-- ------------------------------------------------------
5:-- Server version	10.1.13-MariaDB
6:
7:/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
8:/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
9:/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
10:/*!40101 SET NAMES utf8 */;
11:/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
12:/*!40103 SET TIME_ZONE='+00:00' */;
13:/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
14:/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
15:/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
16:/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
17:
18:--
19:-- Table structure for table `comment_photo`
20:--
21:
22:DROP TABLE IF EXISTS `comment_photo`;
23:/*!40101 SET @saved_cs_client     = @@character_set_client */;
24:/*!40101 SET character_set_client = utf8 */;
25:CREATE TABLE `comment_photo` (
26:  `cno` int(11) NOT NULL AUTO_INCREMENT,
27:  `bno` int(11) NOT NULL,
28:  `comment` text,
29:  `writer` varchar(50) DEFAULT NULL,
30:  `pass` varchar(50) DEFAULT NULL,
31:  `secret` char(2) DEFAULT NULL,
32:  `star` char(10) DEFAULT NULL,
33:  `cdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
34:  `file` varchar(250) DEFAULT NULL,
35:  PRIMARY KEY (`cno`)
36:) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
37:/*!40101 SET character_set_client = @saved_cs_client */;
38:
39:--
40:-- Dumping data for table `comment_photo`
41:--
42:
43:LOCK TABLES `comment_photo` WRITE;
44:/*!40000 ALTER TABLE `comment_photo` DISABLE KEYS */;
46:/*!40000 ALTER TABLE `comment_photo` ENABLE KEYS */;
47:UNLOCK TABLES;
48:
49:--
50:-- Table structure for table `index1board1`
51:--
52:
53:DROP TABLE IF EXISTS `index1board1`;
54:/*!40101 SET @saved_cs_client     = @@character_set_client */;
55:/*!40101 SET character_set_client = utf8 */;
56:CREATE TABLE `index1board1` (
57:  `bno` int(11) NOT NULL AUTO_INCREMENT,
58:  `btitle` varchar(250) NOT NULL,
59:  `bcontent` text NOT NULL,
60:  `bfile` varchar(250) NOT NULL,
61:  `bcategory` varchar(100) NOT NULL,
62:  `blink` text,
63:  PRIMARY KEY (`bno`)
64:) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
65:/*!40101 SET character_set_client = @saved_cs_client */;
66:
67:--
68:-- Dumping data for table `index1board1`
69:--
70:
71:LOCK TABLES `index1board1` WRITE;
72:/*!40000 ALTER TABLE `index1board1` DISABLE KEYS */;
74:/*!40000 ALTER TABLE `index1board1` ENABLE KEYS */;
75:UNLOCK TABLES;
76:
77:--
78:-- Table structure for table `index1board2`
79:--
80:
81:DROP TABLE IF EXISTS `index1board2`;
82:/*!40101 SET @saved_cs_client     = @@character_set_client */;
83:/*!40101 SET character_set_client = utf8 */;
84:CREATE TABLE `index1board2` (
85:  `bno` int(11) NOT NULL AUTO_INCREMENT,
86:  `bisbn` varchar(150) NOT NULL,
87:  `bauthor` varchar(100) NOT NULL,
88:  `btitle` varchar(250) NOT NULL,
89:  `bcontent` text NOT NULL,
90:  `bfile` text NOT NULL,
91:  `blink` text NOT NULL,
92:  `bcategory` varchar(50) NOT NULL,
93:  PRIMARY KEY (`bno`)
94:) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
95:/*!40101 SET character_set_client = @saved_cs_client */;
96:
97:--
98:-- Dumping data for table `index1board2`
99:--
100:
101:LOCK TABLES `index1board2` WRITE;
102:/*!40000 ALTER TABLE `index1board2` DISABLE KEYS */;
104:/*!40000 ALTER TABLE `index1board2` ENABLE KEYS */;
105:UNLOCK TABLES;
106:
107:--
108:-- Table structure for table `index1board3`
109:--
110:
111:DROP TABLE IF EXISTS `index1board3`;
112:/*!40101 SET @saved_cs_client     = @@character_set_client */;
113:/*!40101 SET character_set_client = utf8 */;
114:CREATE TABLE `index1board3` (
115:  `bno` int(11) NOT NULL AUTO_INCREMENT,
116:  `bauthor` varchar(100) NOT NULL,
117:  `blink` text,
118:  `btitle` varchar(250) NOT NULL,
119:  `bcontent` text NOT NULL,
120:  `bcategory` varchar(100) NOT NULL,
121:  `bfile` text NOT NULL,
122:  PRIMARY KEY (`bno`)
123:) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
124:/*!40101 SET character_set_client = @saved_cs_client */;
125:
126:--
127:-- Dumping data for table `index1board3`
128:--
129:
130:LOCK TABLES `index1board3` WRITE;
131:/*!40000 ALTER TABLE `index1board3` DISABLE KEYS */;
133:/*!40000 ALTER TABLE `index1board3` ENABLE KEYS */;
134:UNLOCK TABLES;
135:
136:--
137:-- Table structure for table `index1board3comment`
138:--
139:
140:DROP TABLE IF EXISTS `index1board3comment`;
141:/*!40101 SET @saved_cs_client     = @@character_set_client */;
142:/*!40101 SET character_set_client = utf8 */;
143:CREATE TABLE `index1board3comment` (
144:  `cno` int(10) NOT NULL AUTO_INCREMENT,
145:  `bno` int(10) NOT NULL,
146:  `comment` text,
147:  `writer` varchar(50) DEFAULT NULL,
148:  `pass` varchar(50) DEFAULT NULL,
149:  `secret` char(1) DEFAULT NULL,
150:  `cdate` datetime DEFAULT CURRENT_TIMESTAMP,
151:  `cpost` varchar(50) NOT NULL,
152:  PRIMARY KEY (`cno`)
153:) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
154:/*!40101 SET character_set_client = @saved_cs_client */;
155:
156:--
157:-- Dumping data for table `index1board3comment`
158:--
159:
160:LOCK TABLES `index1board3comment` WRITE;
161:/*!40000 ALTER TABLE `index1board3comment` DISABLE KEYS */;
163:/*!40000 ALTER TABLE `index1board3comment` ENABLE KEYS */;
164:UNLOCK TABLES;
165:
166:--
167:-- Table structure for table `index1view1`
168:--
169:
170:DROP TABLE IF EXISTS `index1view1`;
171:/*!40101 SET @saved_cs_client     = @@character_set_client */;
172:/*!40101 SET character_set_client = utf8 */;
173:CREATE TABLE `index1view1` (
174:  `bno` int(11) NOT NULL AUTO_INCREMENT,
175:  `bname` varchar(50) NOT NULL,
176:  `bpass` varchar(50) NOT NULL,
177:  `btitle` varchar(250) NOT NULL,
178:  `bcontent` text NOT NULL,
179:  `bfile` text NOT NULL,
180:  `bdate` varchar(100) NOT NULL,
181:  `bhit` int(11) NOT NULL DEFAULT '0',
182:  `bip` varchar(20) NOT NULL,
183:  `bgroup` int(11) NOT NULL DEFAULT '0',
184:  `bstep` int(11) NOT NULL DEFAULT '0',
185:  `bdepth` int(11) NOT NULL DEFAULT '0',
186:  PRIMARY KEY (`bno`)
187:) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
188:/*!40101 SET character_set_client = @saved_cs_client */;
189:
190:--
191:-- Dumping data for table `index1view1`
192:--
193:
194:LOCK TABLES `index1view1` WRITE;
195:/*!40000 ALTER TABLE `index1view1` DISABLE KEYS */;
197:/*!40000 ALTER TABLE `index1view1` ENABLE KEYS */;
198:UNLOCK TABLES;
199:
200:--
201:-- Table structure for table `index1view1file1`
202:--
203:
204:DROP TABLE IF EXISTS `index1view1file1`;
205:/*!40101 SET @saved_cs_client     = @@character_set_client */;
206:/*!40101 SET character_set_client = utf8 */;
207:CREATE TABLE `index1view1file1` (
208:  `bno` int(11) NOT NULL AUTO_INCREMENT,
209:  `bfile` text NOT NULL,
210:  PRIMARY KEY (`bno`)
211:) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
212:/*!40101 SET character_set_client = @saved_cs_client */;
213:
214:--
215:-- Dumping data for table `index1view1file1`
216:--
217:
218:LOCK TABLES `index1view1file1` WRITE;
219:/*!40000 ALTER TABLE `index1view1file1` DISABLE KEYS */;
221:/*!40000 ALTER TABLE `index1view1file1` ENABLE KEYS */;
222:UNLOCK TABLES;
223:
224:--
225:-- Table structure for table `member`
226:--
227:
228:DROP TABLE IF EXISTS `member`;
229:/*!40101 SET @saved_cs_client     = @@character_set_client */;
230:/*!40101 SET character_set_client = utf8 */;
231:CREATE TABLE `member` (
232:  `mbno` int(11) NOT NULL AUTO_INCREMENT,
233:  `mbid` varchar(100) NOT NULL,
234:  `mbpass` varchar(100) NOT NULL,
235:  `mbgender` varchar(10) NOT NULL,
236:  `mbemail` varchar(100) NOT NULL,
237:  `mbmobile` varchar(100) NOT NULL,
238:  `mbaddress` varchar(200) NOT NULL,
239:  `mblike` varchar(100) NOT NULL,
240:  `mbagree` varchar(100) NOT NULL,
241:  `mbdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
242:  `mbip` varchar(100) DEFAULT NULL,
243:  PRIMARY KEY (`mbno`)
244:) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
245:/*!40101 SET character_set_client = @saved_cs_client */;
246:
247:--
248:-- Dumping data for table `member`
249:--
250:
251:LOCK TABLES `member` WRITE;
252:/*!40000 ALTER TABLE `member` DISABLE KEYS */;
254:/*!40000 ALTER TABLE `member` ENABLE KEYS */;
255:UNLOCK TABLES;
256:
257:--
258:-- Table structure for table `notice1board1`
259:--
260:
261:DROP TABLE IF EXISTS `notice1board1`;
262:/*!40101 SET @saved_cs_client     = @@character_set_client */;
263:/*!40101 SET character_set_client = utf8 */;
264:CREATE TABLE `notice1board1` (
265:  `bno` int(11) NOT NULL AUTO_INCREMENT,
266:  `bname` varchar(50) NOT NULL,
267:  `bpass` varchar(50) NOT NULL,
268:  `btitle` varchar(250) NOT NULL,
269:  `bcontent` text NOT NULL,
270:  `bfile` text NOT NULL,
271:  `bdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
272:  `bhit` int(11) NOT NULL DEFAULT '0',
273:  `bip` varchar(20) DEFAULT NULL,
274:  `bgroup` int(11) NOT NULL DEFAULT '0',
275:  `bstep` int(11) NOT NULL DEFAULT '0',
276:  `bdepth` int(11) NOT NULL DEFAULT '0',
277:  PRIMARY KEY (`bno`)
278:) ENGINE=InnoDB AUTO_INCREMENT=380 DEFAULT CHARSET=utf8;
279:/*!40101 SET character_set_client = @saved_cs_client */;
280:
281:--
282:-- Dumping data for table `notice1board1`
283:--
284:
285:LOCK TABLES `notice1board1` WRITE;
286:/*!40000 ALTER TABLE `notice1board1` DISABLE KEYS */;
288:/*!40000 ALTER TABLE `notice1board1` ENABLE KEYS */;
289:UNLOCK TABLES;
290:
291:--
292:-- Table structure for table `searchtest`
293:--
294:
295:DROP TABLE IF EXISTS `searchtest`;
296:/*!40101 SET @saved_cs_client     = @@character_set_client */;
297:/*!40101 SET character_set_client = utf8 */;
298:CREATE TABLE `searchtest` (
299:  `no` int(11) NOT NULL AUTO_INCREMENT,
300:  `title` varchar(250) NOT NULL,
301:  `content` text NOT NULL,
302:  PRIMARY KEY (`no`)
303:) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
304:/*!40101 SET character_set_client = @saved_cs_client */;
305:
306:--
307:-- Dumping data for table `searchtest`
308:--
309:
310:LOCK TABLES `searchtest` WRITE;
311:/*!40000 ALTER TABLE `searchtest` DISABLE KEYS */;
313:/*!40000 ALTER TABLE `searchtest` ENABLE KEYS */;
314:UNLOCK TABLES;
315:
316:--
317:-- Table structure for table `spring1board1`
318:--
319:
320:DROP TABLE IF EXISTS `spring1board1`;
321:/*!40101 SET @saved_cs_client     = @@character_set_client */;
322:/*!40101 SET character_set_client = utf8 */;
323:CREATE TABLE `spring1board1` (
324:  `bno` int(11) NOT NULL AUTO_INCREMENT,
325:  `bname` varchar(50) NOT NULL,
326:  `bpass` varchar(50) NOT NULL,
327:  `btitle` varchar(100) NOT NULL,
328:  `bcontent` text NOT NULL,
329:  `bdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
330:  `bhit` int(11) NOT NULL DEFAULT '0',
331:  `bgroup` int(11) DEFAULT '0',
332:  `bstep` int(11) DEFAULT '0',
333:  `bindent` int(11) DEFAULT '0',
334:  PRIMARY KEY (`bno`)
335:) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
336:/*!40101 SET character_set_client = @saved_cs_client */;
337:
338:--
339:-- Dumping data for table `spring1board1`
340:--
341:
342:LOCK TABLES `spring1board1` WRITE;
343:/*!40000 ALTER TABLE `spring1board1` DISABLE KEYS */;
345:/*!40000 ALTER TABLE `spring1board1` ENABLE KEYS */;
346:UNLOCK TABLES;
347:
348:--
349:-- Table structure for table `spring1board2`
350:--
351:
352:DROP TABLE IF EXISTS `spring1board2`;
353:/*!40101 SET @saved_cs_client     = @@character_set_client */;
354:/*!40101 SET character_set_client = utf8 */;
355:CREATE TABLE `spring1board2` (
356:  `bno` int(11) NOT NULL AUTO_INCREMENT,
357:  `bname` varchar(50) NOT NULL,
358:  `bpass` varchar(50) NOT NULL,
359:  `btitle` varchar(100) NOT NULL,
360:  `bcontent` text NOT NULL,
361:  `bdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
362:  `bhit` int(11) NOT NULL DEFAULT '0',
363:  `bgroup` int(11) DEFAULT '0',
364:  `bstep` int(11) DEFAULT '0',
365:  `bindent` int(11) DEFAULT '0',
366:  `bfile` text,
367:  PRIMARY KEY (`bno`)
368:) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
369:/*!40101 SET character_set_client = @saved_cs_client */;
370:
371:--
372:-- Dumping data for table `spring1board2`
373:--
374:
375:LOCK TABLES `spring1board2` WRITE;
376:/*!40000 ALTER TABLE `spring1board2` DISABLE KEYS */;
378:/*!40000 ALTER TABLE `spring1board2` ENABLE KEYS */;
379:UNLOCK TABLES;
380:
381:--
382:-- Table structure for table `spring1comment1`
383:--
384:
385:DROP TABLE IF EXISTS `spring1comment1`;
386:/*!40101 SET @saved_cs_client     = @@character_set_client */;
387:/*!40101 SET character_set_client = utf8 */;
388:CREATE TABLE `spring1comment1` (
389:  `cno` int(11) NOT NULL AUTO_INCREMENT,
390:  `bno` int(11) NOT NULL,
391:  `comment` text,
392:  `writer` varchar(50) DEFAULT NULL,
393:  `pass` varchar(50) DEFAULT NULL,
394:  `secret` char(1) DEFAULT NULL,
395:  `cdate` datetime DEFAULT CURRENT_TIMESTAMP,
396:  PRIMARY KEY (`cno`)
397:) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
398:/*!40101 SET character_set_client = @saved_cs_client */;
399:
400:--
401:-- Dumping data for table `spring1comment1`
402:--
403:
404:LOCK TABLES `spring1comment1` WRITE;
405:/*!40000 ALTER TABLE `spring1comment1` DISABLE KEYS */;
407:/*!40000 ALTER TABLE `spring1comment1` ENABLE KEYS */;
408:UNLOCK TABLES;
409:
410:--
411:-- Table structure for table `spring1comment2`
412:--
413:
414:DROP TABLE IF EXISTS `spring1comment2`;
415:/*!40101 SET @saved_cs_client     = @@character_set_client */;
416:/*!40101 SET character_set_client = utf8 */;
417:CREATE TABLE `spring1comment2` (
418:  `cno` int(11) NOT NULL AUTO_INCREMENT,
419:  `bno` int(11) NOT NULL,
420:  `comment` text,
421:  `writer` varchar(50) DEFAULT NULL,
422:  `pass` varchar(50) DEFAULT NULL,
423:  `secret` char(1) DEFAULT NULL,
424:  `cdate` datetime DEFAULT CURRENT_TIMESTAMP,
425:  PRIMARY KEY (`cno`)
426:) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
427:/*!40101 SET character_set_client = @saved_cs_client */;
428:
429:--
430:-- Dumping data for table `spring1comment2`
431:--
432:
433:LOCK TABLES `spring1comment2` WRITE;
434:/*!40000 ALTER TABLE `spring1comment2` DISABLE KEYS */;
436:/*!40000 ALTER TABLE `spring1comment2` ENABLE KEYS */;
437:UNLOCK TABLES;
438:
439:--
440:-- Table structure for table `usercheck`
441:--
442:
443:DROP TABLE IF EXISTS `usercheck`;
444:/*!40101 SET @saved_cs_client     = @@character_set_client */;
445:/*!40101 SET character_set_client = utf8 */;
446:CREATE TABLE `usercheck` (
447:  `uno` int(11) NOT NULL AUTO_INCREMENT,
448:  `userid` varchar(50) NOT NULL,
449:  `uyear` int(11) NOT NULL,
450:  `umonth` int(11) NOT NULL,
451:  `uday` int(11) NOT NULL,
452:  `ucheck` varchar(50) DEFAULT NULL,
453:  `udate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
454:  PRIMARY KEY (`uno`)
455:) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
456:/*!40101 SET character_set_client = @saved_cs_client */;
457:
458:--
459:-- Dumping data for table `usercheck`
460:--
461:
462:LOCK TABLES `usercheck` WRITE;
463:/*!40000 ALTER TABLE `usercheck` DISABLE KEYS */;
465:/*!40000 ALTER TABLE `usercheck` ENABLE KEYS */;
466:UNLOCK TABLES;
467:
468:--
469:-- Table structure for table `userlike`
470:--
471:
472:DROP TABLE IF EXISTS `userlike`;
473:/*!40101 SET @saved_cs_client     = @@character_set_client */;
474:/*!40101 SET character_set_client = utf8 */;
475:CREATE TABLE `userlike` (
476:  `uno` int(11) NOT NULL AUTO_INCREMENT,
477:  `userid` varchar(50) NOT NULL,
478:  `utitle` text NOT NULL,
479:  `uwriter` text NOT NULL,
480:  `uisbn` varchar(200) NOT NULL,
481:  `ufile` text NOT NULL,
482:  `uprice` varchar(100) NOT NULL,
483:  `udate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
484:  PRIMARY KEY (`uno`)
485:) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
486:/*!40101 SET character_set_client = @saved_cs_client */;
487:
488:--
489:-- Dumping data for table `userlike`
490:--
491:
492:LOCK TABLES `userlike` WRITE;
493:/*!40000 ALTER TABLE `userlike` DISABLE KEYS */;
495:/*!40000 ALTER TABLE `userlike` ENABLE KEYS */;
496:UNLOCK TABLES;
497:/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
498:
499:/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
500:/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
501:/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
502:/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
503:/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
504:/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
505:/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
506:
507:-- Dump completed on 2019-08-12 18:20:13
