import os
import requests
import re
from bs4 import BeautifulSoup
import sys

os.getcwd() # current working directory
os.chdir('\Programming\Python\midterm_report')   # change current working directory

# get the current encoding
type = sys.getfilesystemencoding()
# request the webpage
req = requests.get("http://www.imdb.com/chart/top?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=2417962742&pf_rd_r=0M85G1V8JHW928EHBETF&pf_rd_s=right-4&pf_rd_t=15506&pf_rd_i=moviemeter&ref_=chtmvm_ql_3")
page = req.text

soup = BeautifulSoup(page, 'html.parser')

# get top 250 movie names and years, may take ~30 seconds
movie_names = []
movie_year = [0] * 250

j = 0
for i in range(250):
    content = str(soup.findAll('td', {'class': 'titleColumn'})[i])
    content = content.decode("UTF-8").encode(type)
    name = re.findall('>(.*?)</a>', content)
    movie_names.insert(len(movie_names), name)

    year = str(soup.findAll('span', {'class': 'secondaryInfo'})[i])
    movie_year[i] = int(re.findall(r"\(([0-9_]+)\)", year)[0])

    # keep track of the progress
    print('We now have ' + str(j) + ' movies')
    j = j + 1

# export to the text file
open("top250names.txt", "w").write("\n".join(("\t".join(item)) for item in movie_names))

