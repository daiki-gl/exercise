function findContract() {
  const name = document.getElementById('name').value
  const nameKana = document.getElementById('nameKana').value
  const birthday = document.getElementById('dob').value
  const company = document.getElementById('company').value
  const contractNum = parseInt(document.getElementById('contractNum').value, 10)

  fetch('http://localhost:3000/search', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      RequestCommon: {
        ReqInfo: {
          time: new Date().toISOString(),
          test: 'aaaaaaaaaaaaaaaaa',
        },
      },
      RequestIndividual: {
        SearchInfo: {
          SearchInfoDetail: {
            Name: '山田　太郎',
            NameKana: 'ヤマダ　タロウ',
            Birthday: '1980-01-01',
            Company: 'Company A',
            ContractNum: contractNum ? [contractNum] : [],
            // ContractNum: [123456],
          },
        },
      },
    }),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok')
      }
      document.body.innerHTML = ''
      return response.text()
    })
    .then((html) => {
      // console.log(html)
      document.body.innerHTML = html
    })
    .catch((error) => console.error('Error:', error))
}
