clear all
close all

tic

url = 'http://www.nuk.edu.tw/bin/home.php';

n = 30;

[u,l] = crawling(url,n);
%pageScore = pagerank(u,l)  % computing each page's score by the page rank algorithm

toc




