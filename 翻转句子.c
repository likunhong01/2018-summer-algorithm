#define _CRT_SECURE_NO_WARNINGS 1

#include<stdio.h>
#include<stdlib.h>
#include<string.h>


char* reserve(char* p, int len);

int main()
{
	char x[100] = "student a am i";
	int len = strlen(x);
	char *px = x;//�ַ���ָ��

	char *rpx;//��ת�˵��ַ�����ָ��
	char *first, *secend;//����ָ�򵥴�ͷβ��ָ��

	printf("%s\n", x);

	rpx = reserve(px, len);
	//һ��ʼ��ָ��ͷ
	first = rpx;
	secend = rpx;
	
	while (*secend != '\0')
	{
		char* sign;//���Ǻ�
		while (*secend != ' ' && *secend != '\0')
		{
			secend++;
		}
		sign = secend;//ָ��ǰ���ʵĿո�ĺ���һ���ַ�,һ���ǿո�
		secend--;//secendָ�򵥴����һ����ĸ
		while (first <secend)//��ʼ�ߵ�
		{
			char tmp = *first;
			*first = *secend;
			*secend = tmp;
			first++;
			secend--;
		}
		if (*sign == '\0')//����ַ��������˾�����
			break;
		while (*sign == ' ')//sign����Ų�������ո�
		{
			sign++;
		}
		//��first��secend��ֵsign
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