### Spring-Boot
* ~~gradle版本从4.8.2升级成5.4.1直接不能用了，很尴尬，后续解决吧~~
* ~~同上，版本升级后，querydsl processor not found。~~
* ~~build有点慢。后续优化下。~~
* ~~IDEA所有test爆红（gradle-version 5.4.1)~~

总结就是，当gradle版本升级之后(gradle 4.8.2可用)，直接不能用了，首当其冲的就是querydsl annocation processer。
看看怎么解决一下，不行的话就换个插件。

2019年6月6日23:43:48 解决，gradle版本为5.4.1，换成了Kotlin的kpat，但是和Lombok有冲突，因此去掉lombok相关代码