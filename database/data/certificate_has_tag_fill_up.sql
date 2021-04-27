-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: certificate_system
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `certificate_has_tag`
--

LOCK TABLES `certificate_has_tag` WRITE;
/*!40000 ALTER TABLE `certificate_has_tag` DISABLE KEYS */;
INSERT INTO `certificate_has_tag` VALUES (32,11),(32,12),(33,13),(33,14),(34,15),(34,16),(35,17),(35,18),(36,19),(36,20),(37,21),(37,22),(38,23),(38,24),(39,25),(39,26),(40,27),(40,28),(41,29),(41,30),(42,31),(42,32),(43,33),(43,34),(44,36),(45,37),(45,38),(46,39),(46,40),(47,41),(47,42),(56,43),(78,43),(48,44),(49,45),(49,46),(50,47),(50,48),(51,49),(51,50),(52,51),(52,52),(53,53),(53,54),(54,55),(54,56),(56,59),(56,60),(57,61),(57,62),(58,63),(58,64),(59,65),(59,66),(60,67),(60,68),(61,69),(61,70),(62,71),(62,72),(63,73),(63,74),(64,75),(64,76),(65,77),(65,78),(78,78),(66,79),(66,80),(67,81),(67,82),(68,83),(68,84),(69,85),(69,86),(70,87),(70,88),(71,89),(71,90),(72,91),(72,92),(73,93),(73,94),(74,95),(74,96),(75,97),(75,98),(76,99),(76,100),(77,101),(77,102),(78,103),(78,104),(79,105),(79,106),(80,107),(80,108),(81,109),(81,110),(82,111),(82,112),(83,113),(83,114),(84,115),(84,116),(85,117),(85,118),(86,119),(86,120),(87,121),(87,122),(88,123),(88,124),(89,125),(89,126),(90,127),(90,128),(91,129),(91,130),(92,131),(92,132),(93,133),(93,134),(94,135),(94,136),(95,137),(95,138),(96,139),(96,140),(97,141),(97,142),(98,143),(98,144),(99,145),(99,146),(100,147),(100,148),(101,149),(101,150),(102,151),(102,152),(103,153),(103,154),(104,155),(104,156),(105,157),(105,158),(106,159),(106,160),(107,161),(107,162),(108,163),(108,164),(109,165),(109,166),(110,167),(110,168),(111,169),(111,170),(112,171),(112,172),(113,173),(113,174),(114,175),(114,176),(115,177),(115,178),(116,179),(116,180),(117,181),(117,182),(118,183),(118,184),(119,185),(119,186),(120,187),(120,188),(122,191),(122,192),(123,193),(123,194),(124,195),(124,196),(125,197),(125,198),(126,199),(126,200),(127,201),(127,202),(128,203),(128,204),(129,205),(129,206),(130,207),(130,208),(131,209),(131,210),(132,211),(132,212),(133,213),(133,214),(134,215),(134,216),(135,217),(135,218),(136,219),(136,220),(137,221),(137,222),(138,223),(138,224),(139,225),(139,226),(140,227),(140,228),(141,229),(141,230),(142,231),(142,232),(143,233),(143,234),(144,235),(144,236),(145,237),(145,238),(146,239),(146,240),(147,241),(147,242),(148,243),(148,244),(149,245),(149,246),(150,247),(150,248),(151,249),(151,250),(152,251),(152,252),(153,253),(153,254),(154,255),(154,256),(155,257),(155,258),(156,259),(156,260),(157,261),(157,262),(158,263),(158,264),(159,265),(159,266),(160,267),(160,268),(161,269),(161,270),(162,271),(162,272),(163,273),(163,274),(164,275),(164,276),(165,277),(165,278),(166,279),(166,280),(167,281),(167,282),(168,283),(168,284),(169,285),(169,286),(170,287),(170,288),(171,289),(171,290),(172,291),(172,292),(173,293),(173,294),(174,295),(174,296),(175,297),(175,298),(176,299),(176,300),(177,301),(177,302),(178,303),(178,304),(179,305),(179,306),(180,307),(180,308),(181,309),(181,310),(182,311),(182,312),(183,313),(183,314),(184,315),(184,316),(185,317),(185,318),(186,319),(186,320),(187,321),(187,322),(188,323),(188,324),(189,325),(189,326),(190,327),(190,328),(191,329),(191,330),(192,331),(192,332),(193,333),(193,334),(194,335),(194,336),(195,337),(195,338),(196,339),(196,340),(197,341),(197,342),(198,343),(198,344),(199,345),(199,346),(200,347),(200,348),(201,349),(201,350),(202,351),(202,352),(203,353),(203,354),(204,355),(204,356),(205,357),(205,358),(206,359),(206,360),(207,361),(207,362),(208,363),(208,364),(209,365),(209,366),(210,367),(210,368),(211,369),(211,370),(212,371),(212,372),(213,373),(213,374),(214,375),(214,376),(215,377),(215,378),(216,379),(216,380),(217,381),(217,382),(218,383),(218,384),(219,385),(219,386),(220,387),(220,388),(221,389),(221,390),(222,391),(222,392),(223,393),(223,394),(224,395),(224,396),(225,397),(225,398),(226,399),(226,400),(227,401),(227,402),(228,403),(228,404),(229,405),(229,406),(230,407),(230,408),(231,409),(231,410),(232,411),(232,412),(233,413),(233,414),(234,415),(234,416),(235,417),(235,418),(236,419),(236,420),(237,421),(237,422),(238,423),(238,424),(239,425),(239,426),(240,427),(240,428),(241,429),(241,430),(242,431),(242,432),(243,433),(243,434),(244,435),(244,436),(245,437),(245,438),(246,439),(246,440),(247,441),(247,442),(248,443),(248,444),(249,445),(249,446),(250,447),(250,448),(251,449),(251,450),(252,451),(252,452),(253,453),(253,454),(254,455),(254,456),(255,457),(255,458),(256,459),(256,460),(257,461),(257,462),(258,463),(258,464),(259,465),(259,466),(260,467),(260,468),(261,469),(261,470),(262,471),(262,472),(263,473),(263,474),(264,475),(264,476),(265,477),(265,478),(266,479),(266,480),(267,481),(267,482),(268,483),(268,484),(269,485),(269,486),(270,487),(270,488),(271,489),(271,490),(272,491),(272,492),(273,493),(273,494),(274,495),(274,496),(275,497),(275,498),(276,499),(276,500),(277,501),(277,502),(278,503),(278,504),(279,505),(279,506),(280,507),(280,508),(281,509),(281,510),(282,511),(282,512),(283,513),(283,514),(529,514),(284,515),(528,515),(529,515),(284,516),(527,516),(530,516),(285,517),(526,517),(530,517),(285,518),(525,518),(531,518),(286,519),(524,519),(531,519),(286,520),(523,520),(532,520),(287,521),(522,521),(532,521),(287,522),(521,522),(533,522),(288,523),(520,523),(533,523),(288,524),(519,524),(534,524),(289,525),(518,525),(534,525),(289,526),(517,526),(535,526),(290,527),(516,527),(535,527),(290,528),(515,528),(536,528),(291,529),(514,529),(536,529),(291,530),(513,530),(537,530),(292,531),(512,531),(537,531),(292,532),(511,532),(538,532),(293,533),(510,533),(538,533),(293,534),(509,534),(539,534),(294,535),(508,535),(539,535),(294,536),(507,536),(540,536),(295,537),(506,537),(540,537),(295,538),(505,538),(541,538),(296,539),(504,539),(541,539),(296,540),(503,540),(542,540),(297,541),(502,541),(542,541),(297,542),(501,542),(543,542),(298,543),(500,543),(543,543),(298,544),(499,544),(544,544),(299,545),(498,545),(544,545),(299,546),(497,546),(545,546),(300,547),(496,547),(545,547),(300,548),(495,548),(546,548),(301,549),(494,549),(546,549),(301,550),(493,550),(547,550),(302,551),(492,551),(547,551),(302,552),(491,552),(548,552),(303,553),(490,553),(548,553),(303,554),(489,554),(549,554),(304,555),(488,555),(549,555),(304,556),(487,556),(550,556),(305,557),(486,557),(550,557),(305,558),(485,558),(551,558),(306,559),(484,559),(551,559),(306,560),(483,560),(552,560),(307,561),(482,561),(552,561),(307,562),(481,562),(553,562),(308,563),(480,563),(553,563),(308,564),(479,564),(554,564),(309,565),(478,565),(554,565),(309,566),(477,566),(555,566),(310,567),(476,567),(555,567),(310,568),(475,568),(556,568),(311,569),(474,569),(556,569),(311,570),(473,570),(557,570),(312,571),(472,571),(557,571),(312,572),(471,572),(558,572),(313,573),(470,573),(558,573),(313,574),(469,574),(559,574),(314,575),(468,575),(559,575),(314,576),(467,576),(560,576),(315,577),(466,577),(560,577),(315,578),(465,578),(561,578),(316,579),(464,579),(561,579),(316,580),(463,580),(562,580),(317,581),(462,581),(562,581),(317,582),(461,582),(563,582),(318,583),(460,583),(563,583),(318,584),(459,584),(564,584),(319,585),(458,585),(564,585),(319,586),(457,586),(565,586),(320,587),(456,587),(565,587),(320,588),(455,588),(566,588),(321,589),(454,589),(566,589),(321,590),(453,590),(567,590),(322,591),(452,591),(567,591),(322,592),(451,592),(568,592),(323,593),(450,593),(568,593),(323,594),(449,594),(569,594),(324,595),(448,595),(569,595),(324,596),(447,596),(570,596),(325,597),(446,597),(570,597),(325,598),(445,598),(571,598),(326,599),(444,599),(571,599),(1050,599),(326,600),(443,600),(572,600),(327,601),(442,601),(572,601),(327,602),(441,602),(573,602),(328,603),(440,603),(573,603),(328,604),(439,604),(574,604),(329,605),(438,605),(574,605),(329,606),(437,606),(575,606),(330,607),(436,607),(575,607),(330,608),(435,608),(576,608),(331,609),(434,609),(576,609),(331,610),(433,610),(577,610),(332,611),(432,611),(577,611),(332,612),(431,612),(578,612),(333,613),(430,613),(578,613),(333,614),(429,614),(579,614),(334,615),(428,615),(579,615),(334,616),(427,616),(580,616),(335,617),(426,617),(580,617),(335,618),(425,618),(581,618),(336,619),(424,619),(581,619),(336,620),(423,620),(582,620),(337,621),(422,621),(582,621),(337,622),(421,622),(583,622),(338,623),(420,623),(583,623),(338,624),(419,624),(584,624),(339,625),(418,625),(584,625),(339,626),(417,626),(585,626),(340,627),(416,627),(585,627),(340,628),(415,628),(586,628),(341,629),(414,629),(586,629),(341,630),(413,630),(587,630),(342,631),(412,631),(587,631),(342,632),(411,632),(588,632),(343,633),(410,633),(588,633),(343,634),(409,634),(589,634),(344,635),(408,635),(589,635),(344,636),(407,636),(590,636),(345,637),(406,637),(590,637),(345,638),(405,638),(591,638),(346,639),(404,639),(591,639),(346,640),(403,640),(592,640),(347,641),(402,641),(592,641),(347,642),(401,642),(593,642),(348,643),(400,643),(593,643),(348,644),(399,644),(594,644),(349,645),(398,645),(594,645),(349,646),(397,646),(595,646),(350,647),(396,647),(595,647),(350,648),(395,648),(596,648),(351,649),(394,649),(596,649),(351,650),(393,650),(597,650),(352,651),(392,651),(597,651),(352,652),(391,652),(598,652),(353,653),(390,653),(598,653),(353,654),(386,654),(389,654),(599,654),(354,655),(388,655),(599,655),(354,656),(387,656),(600,656),(355,657),(386,657),(600,657),(1049,657),(355,658),(385,658),(601,658),(356,659),(384,659),(601,659),(356,660),(383,660),(602,660),(357,661),(382,661),(602,661),(357,662),(381,662),(603,662),(358,663),(380,663),(603,663),(358,664),(379,664),(604,664),(359,665),(378,665),(604,665),(359,666),(377,666),(605,666),(360,667),(376,667),(605,667),(360,668),(375,668),(606,668),(361,669),(374,669),(606,669),(361,670),(373,670),(607,670),(362,671),(372,671),(607,671),(362,672),(371,672),(608,672),(363,673),(370,673),(608,673),(363,674),(369,674),(609,674),(364,675),(368,675),(609,675),(364,676),(367,676),(610,676),(365,677),(366,677),(610,677),(365,678),(611,678),(364,679),(611,679),(363,680),(612,680),(362,681),(612,681),(361,682),(613,682),(360,683),(613,683),(359,684),(614,684),(358,685),(614,685),(357,686),(615,686),(356,687),(615,687),(355,688),(616,688),(354,689),(616,689),(353,690),(617,690),(352,691),(617,691),(351,692),(618,692),(350,693),(618,693),(349,694),(619,694),(348,695),(619,695),(347,696),(346,697),(345,698),(621,698),(344,699),(621,699),(343,700),(622,700),(342,701),(622,701),(341,702),(623,702),(340,703),(623,703),(339,704),(624,704),(338,705),(624,705),(337,706),(625,706),(336,707),(625,707),(335,708),(626,708),(334,709),(626,709),(333,710),(627,710),(332,711),(627,711),(331,712),(628,712),(330,713),(628,713),(329,714),(629,714),(328,715),(629,715),(327,716),(630,716),(326,717),(630,717),(325,718),(631,718),(324,719),(631,719),(323,720),(632,720),(322,721),(632,721),(321,722),(633,722),(320,723),(633,723),(319,724),(634,724),(318,725),(634,725),(317,726),(635,726),(316,727),(635,727),(315,728),(636,728),(314,729),(636,729),(313,730),(637,730),(312,731),(637,731),(311,732),(638,732),(310,733),(638,733),(309,734),(639,734),(308,735),(639,735),(307,736),(640,736),(306,737),(640,737),(305,738),(641,738),(304,739),(641,739),(303,740),(642,740),(302,741),(642,741),(301,742),(643,742),(300,743),(643,743),(299,744),(644,744),(298,745),(644,745),(297,746),(645,746),(296,747),(645,747),(295,748),(646,748),(294,749),(646,749),(293,750),(647,750),(292,751),(647,751),(291,752),(648,752),(290,753),(648,753),(289,754),(649,754),(288,755),(649,755),(287,756),(650,756),(286,757),(650,757),(285,758),(651,758),(284,759),(651,759),(283,760),(652,760),(282,761),(652,761),(281,762),(653,762),(280,763),(653,763),(279,764),(654,764),(278,765),(654,765),(277,766),(655,766),(276,767),(655,767),(275,768),(656,768),(274,769),(656,769),(273,770),(657,770),(272,771),(657,771),(271,772),(658,772),(270,773),(658,773),(269,774),(659,774),(268,775),(659,775),(267,776),(660,776),(266,777),(660,777),(265,778),(661,778),(264,779),(661,779),(263,780),(662,780),(262,781),(662,781),(261,782),(663,782),(260,783),(663,783),(259,784),(664,784),(258,785),(664,785),(257,786),(665,786),(256,787),(665,787),(255,788),(666,788),(254,789),(666,789),(253,790),(667,790),(252,791),(667,791),(251,792),(668,792),(250,793),(668,793),(249,794),(669,794),(248,795),(669,795),(247,796),(670,796),(246,797),(670,797),(245,798),(671,798),(244,799),(671,799),(243,800),(672,800),(242,801),(672,801),(241,802),(673,802),(240,803),(673,803),(239,804),(674,804),(238,805),(674,805),(237,806),(675,806),(236,807),(675,807),(235,808),(676,808),(234,809),(676,809),(233,810),(677,810),(232,811),(677,811),(231,812),(678,812),(230,813),(678,813),(229,814),(679,814),(228,815),(679,815),(227,816),(680,816),(226,817),(680,817),(225,818),(681,818),(224,819),(681,819),(223,820),(682,820),(222,821),(682,821),(221,822),(683,822),(220,823),(683,823),(219,824),(684,824),(218,825),(684,825),(217,826),(685,826),(216,827),(685,827),(215,828),(686,828),(214,829),(686,829),(213,830),(687,830),(212,831),(687,831),(211,832),(688,832),(210,833),(688,833),(209,834),(689,834),(208,835),(689,835),(207,836),(690,836),(206,837),(690,837),(205,838),(691,838),(204,839),(691,839),(203,840),(692,840),(202,841),(692,841),(201,842),(693,842),(200,843),(693,843),(199,844),(694,844),(198,845),(694,845),(197,846),(695,846),(196,847),(695,847),(195,848),(696,848),(194,849),(696,849),(193,850),(697,850),(192,851),(697,851),(191,852),(698,852),(190,853),(698,853),(189,854),(699,854),(188,855),(699,855),(187,856),(700,856),(186,857),(700,857),(185,858),(701,858),(184,859),(701,859),(183,860),(702,860),(182,861),(702,861),(181,862),(703,862),(180,863),(703,863),(179,864),(704,864),(178,865),(704,865),(177,866),(705,866),(176,867),(705,867),(175,868),(706,868),(174,869),(706,869),(173,870),(707,870),(172,871),(707,871),(171,872),(708,872),(170,873),(708,873),(169,874),(709,874),(168,875),(709,875),(167,876),(710,876),(166,877),(710,877),(165,878),(711,878),(164,879),(711,879),(163,880),(712,880),(162,881),(712,881),(161,882),(713,882),(160,883),(713,883),(159,884),(714,884),(158,885),(714,885),(157,886),(715,886),(156,887),(715,887),(155,888),(716,888),(154,889),(716,889),(153,890),(717,890),(152,891),(717,891),(151,892),(718,892),(150,893),(718,893),(149,894),(719,894),(148,895),(719,895),(147,896),(720,896),(146,897),(720,897),(145,898),(721,898),(144,899),(721,899),(143,900),(722,900),(142,901),(722,901),(141,902),(723,902),(140,903),(723,903),(139,904),(724,904),(138,905),(724,905),(137,906),(725,906),(136,907),(725,907),(135,908),(726,908),(134,909),(726,909),(133,910),(727,910),(132,911),(727,911),(131,912),(728,912),(130,913),(728,913),(129,914),(729,914),(128,915),(729,915),(127,916),(730,916),(126,917),(730,917),(125,918),(731,918),(124,919),(731,919),(123,920),(732,920),(122,921),(732,921),(733,922),(120,923),(733,923),(119,924),(734,924),(118,925),(734,925),(117,926),(735,926),(116,927),(735,927),(115,928),(736,928),(114,929),(736,929),(113,930),(737,930),(112,931),(737,931),(111,932),(738,932),(110,933),(738,933),(109,934),(739,934),(108,935),(739,935),(107,936),(740,936),(106,937),(740,937),(105,938),(741,938),(104,939),(741,939),(103,940),(742,940),(102,941),(742,941),(101,942),(743,942),(100,943),(743,943),(99,944),(744,944),(98,945),(744,945),(97,946),(745,946),(96,947),(745,947),(95,948),(746,948),(94,949),(746,949),(93,950),(747,950),(92,951),(747,951),(91,952),(748,952),(90,953),(748,953),(89,954),(749,954),(88,955),(749,955),(87,956),(750,956),(86,957),(750,957),(85,958),(751,958),(84,959),(751,959),(83,960),(752,960),(82,961),(752,961),(81,962),(753,962),(80,963),(753,963),(79,964),(754,964),(78,965),(754,965),(77,966),(755,966),(76,967),(755,967),(75,968),(756,968),(74,969),(756,969),(73,970),(757,970),(72,971),(757,971),(71,972),(758,972),(70,973),(758,973),(69,974),(759,974),(68,975),(759,975),(67,976),(760,976),(66,977),(760,977),(65,978),(761,978),(64,979),(761,979),(63,980),(762,980),(62,981),(762,981),(61,982),(763,982),(60,983),(763,983),(59,984),(764,984),(58,985),(764,985),(57,986),(765,986),(56,987),(765,987),(766,988),(54,989),(766,989),(53,990),(767,990),(52,991),(767,991),(51,992),(768,992),(50,993),(768,993),(49,994),(769,994),(48,995),(769,995),(47,996),(770,996),(46,997),(770,997),(45,998),(771,998),(44,999),(771,999),(43,1000),(772,1000),(42,1001),(772,1001),(41,1002),(773,1002),(40,1003),(773,1003),(39,1004),(774,1004),(38,1005),(774,1005),(37,1006),(775,1006),(36,1007),(775,1007),(35,1008),(776,1008),(34,1009),(776,1009),(33,1010),(777,1010),(32,1011),(777,1011),(778,1012),(1044,1022),(1045,1022),(1046,1022),(1047,1022),(1048,1022),(1045,1026),(1049,1027),(1050,1028),(44,1033);
/*!40000 ALTER TABLE `certificate_has_tag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-27 17:18:18
