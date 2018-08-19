#define _CRT_SECURE_NO_WARNINGS 1

#include<stdio.h>
#include<stdlib.h>
#include<string.h>


char* reserve(char* p, int len);

int main()
{
	char x[100] = "student a am i";
	int len = strlen(x);
	char *px = x;//字符串指针

	char *rpx;//翻转了的字符串的指针
	char *first, *secend;//两个指向单词头尾的指针

	printf("%s\n", x);

	rpx = reserve(px, len);
	//一开始都指向头
	first = rpx;
	secend = rpx;
	
	while (*secend != '\0')
	{
		char* sign;//留记号
		while (*secend != ' ' && *secend != '\0')
		{
			secend++;
		}
		sign = secend;//指向当前单词的空格的后面一个字符,一般是空格
		secend--;//secend指向单词最后一个字母
		while (first <secend)//开始颠倒
		{
			char tmp = *first;
			*first = *secend;
			*secend = tmp;
			first++;
			secend--;
		}
		if (*sign == '\0')//如果字符串结束了就跳出
			break;
		while (*sign == ' ')//sign往后挪，跳过空格
		{
			sign++;
		}
		//把first和secend赋值sign
		first = sign;
		secend = sign;
	}
	
	printf("%s", x);

	system("pause");
	return 0;
}

char* reserve(char* p, int len)
{
	char *first = p;
	char* secend = p + len - 1;
	while (first < secend)
	{
		char tmp = *first;
		*first = *secend;
		*secend = tmp;
		first++;
		secend--;
	}
	return p;
}