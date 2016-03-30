#!/usr/bin/python
import urllib
import re
import smtplib
from email.mime.text import MIMEText

def send_email(user, pwd, recipient, subject, body):
    #import smtplib

    gmail_user = user
    gmail_pwd = pwd
    FROM = user
    TO = recipient if type(recipient) is list else [recipient]
    SUBJECT = subject
    TEXT = body

    # Prepare actual message
    message = """\From: %s\nTo: %s\nSubject: %s\n\n%s
    """ % (FROM, ", ".join(TO), SUBJECT, TEXT)
    try:
        server = smtplib.SMTP("smtp.gmail.com", 587)
        server.ehlo()
        server.starttls()
        server.login(gmail_user, gmail_pwd)
        server.sendmail(FROM, TO, message)
        server.close()
        print 'successfully sent the mail'
    except:
        print "failed to send mail"

def send_email_ssl(user, pwd, recipient, subject, body):
    #import smtplib

    gmail_user = user
    gmail_pwd = pwd
    FROM = user
    TO = recipient if type(recipient) is list else [recipient]
    SUBJECT = subject
    TEXT = body

    # Prepare actual message
    message = """\From: %s\nTo: %s\nSubject: %s\n\n%s
    """ % (FROM, ", ".join(TO), SUBJECT, TEXT)
    try:
        # SMTP_SSL Example
        server_ssl = smtplib.SMTP_SSL("smtp.gmail.com", 465)
        server_ssl.ehlo() # optional, called by login()
        server_ssl.login(gmail_user, gmail_pwd)  
        # ssl server doesn't support or need tls, so don't call server_ssl.starttls() 
        server_ssl.sendmail(FROM, TO, message)
        #server_ssl.quit()
        server_ssl.close()
        print 'successfully sent the mail'
    except:
        print "failed to send mail"

def main():
  #ufile1=urllib.urlopen('http://www.bestbuy.ca/en-CA/category/laptops-ultrabooks/20352.aspx?path=667d54682e0f5309125ed6faae37dfcden01&NVID=departments;Computers%2C%20Tablets%20%20%26%20eReaders;Laptops%20%26%20Ultrabooks;im;c1;r1;en')
  ufile=urllib.urlopen('http://www.bestbuy.ca/en-CA/category/laptops-ultrabooks/20352.aspx?path=667d54682e0f5309125ed6faae37dfcden01&NVID=departments;Computers%2C%20Tablets%20%20%26%20eReaders;Laptops%20%26%20Ultrabooks;im;c1;r1;en')
  text=ufile.read()
  ufile.close()
  matchLaptop=re.search(r'>.*Laptop.*Windows', text, re.IGNORECASE)
  #One Day ONLY
  textLaptop=matchLaptop.group()
  
  matchPrice=re.search(r'<span class="amount">\$\d+\.\d+', text)
  textLaptop=textLaptop+'\n'+matchPrice.group()

  matchDate=re.search(r'Sale Ends:.*2015', text)
  textLaptop+='\n'+matchDate.group()
  print textLaptop
  #<span class="amount">$649.99</span>
  #<span class="amount">$399.99</span>
  #<span>Sale Ends: </span><span>October 18, 2015</span>


  send_email_ssl('sender@gmail.com', 'psw', ['###@hotmail.com','###@hotmail.com'], 'Bestbuy', textLaptop)
  




if __name__ == '__main__':
  main()
